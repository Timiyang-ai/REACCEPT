@Nonnull
    public TransformResponse execute(@Nonnull final TransformRequest request) throws ScriptException {
        log.trace("entry params({})", request);

        // Verify state
        if (!isRunning()) {
            IllegalStateException e = new IllegalStateException("Transform service has not been started");
            log.error("Throwing {}", e);
            throw e;
        }

        // Generate destination
        String table = newTableName();
        this.cache.put(table, MIN_BYTES);

        // Execute script
        List<NamedParam> bindings = ImmutableList.of((NamedParam) new NamedParamClass("database", "String", DATABASE), new NamedParamClass("tableName", "String", table));

        Object result = this.engine.eval(toScript(request), bindings);
        if (result instanceof Callable) {
            @SuppressWarnings("unchecked")
            Callable<TransformResponse> callable = (Callable)result;
            tracker.submitJob(new TransformJob(table, callable, engine.getSparkContext()));
        } else {
            IllegalStateException e = new IllegalStateException("Unexpected script result type: " + (result != null ? result.getClass() : null));
            log.error("Throwing {}", e);
            throw e;
        }

        // Update table weight
        try {
            updateWeight(table, this.engine.getSQLContext());
        } catch (Exception e) {
            log.warn("Failed to update table weight: {}", e.toString());
        }

        // Build response
        TransformResponse response = new TransformResponse();
        response.setProgress(0.0);
        response.setStatus(TransformResponse.Status.PENDING);
        response.setTable(table);

        log.trace("exit with({})", response);
        return response;
    }