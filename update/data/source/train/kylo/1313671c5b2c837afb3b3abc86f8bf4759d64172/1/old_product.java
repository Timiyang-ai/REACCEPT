@Nonnull
    public String execute (@Nonnull final TransformRequest request) throws ScriptException
    {
        LOG.entry(request);

        // Verify state
        if (!isRunning()) {
            throw LOG.throwing(new IllegalStateException("Transform service has not been started"));
        }

        // Generate destination
        String table = UUID.randomUUID().toString().replace("-", "");
        this.cache.put(table, MIN_BYTES);

        // Execute script
        List<NamedParam> bindings = ImmutableList.of(
                (NamedParam)new NamedParamClass("database", "String", DATABASE),
                new NamedParamClass("tableName", "String", table));

        this.engine.eval(toScript(request), bindings);

        // Update table weight
        try {
            updateWeight(table, this.engine.getSQLContext());
        }
        catch (Exception e) {
            LOG.warn("Failed to update table weight: {}", e.toString());
        }

        LOG.exit(table);
        return table;
    }