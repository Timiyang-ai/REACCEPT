@Nonnull
    public TransformResponse execute(@Nonnull final TransformRequest request)
            throws ScriptException {
        log.trace("entry params({})", request);

        // Verify state
        if (!isRunning()) {
            IllegalStateException e = new IllegalStateException("Transform service has not been started");
            log.error("Throwing {}", e);
            throw e;
        }

        // Generate destination
        String table = UUID.randomUUID().toString().replace("-", "");
        this.cache.put(table, MIN_BYTES);

        // Execute script
        List<NamedParam> bindings = ImmutableList.of((NamedParam) new NamedParamClass("database", "String", DATABASE),
                new NamedParamClass("tableName", "String", table));

        Object results = this.engine.eval(toScript(request), bindings);

        // Update table weight
        try {
            updateWeight(table, this.engine.getSQLContext());
        } catch (Exception e) {
            log.warn("Failed to update table weight: {}", e.toString());
        }

        // Build response
        TransformResponse response = new TransformResponse();
        response.setStatus(TransformResponse.Status.SUCCESS);
        response.setTable(table);

        if (request.isSendResults()) {
            response.setResults((QueryResult) results);
        }

        log.trace("exit with({})", response);
        return response;
    }