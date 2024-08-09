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

        // Build bindings list
        final List<NamedParam> bindings = new ArrayList<>();
        bindings.add(new NamedParamClass("database", "String", DATABASE));
        bindings.add(new NamedParamClass("tableName", "String", table));

        if (request.getDatasources() != null && !request.getDatasources().isEmpty()) {
            final DatasourceProvider datasourceProvider = new DatasourceProvider(request.getDatasources());
            bindings.add(new NamedParamClass("datasourceProvider", DatasourceProvider.class.getName(), datasourceProvider));
        }

        // Execute script
        Object result = this.engine.eval(toScript(request), bindings);

        TransformJob job;
        if (result instanceof Callable) {
            @SuppressWarnings("unchecked")
            Callable<TransformResponse> callable = (Callable) result;
            job = new TransformJob(table, callable, engine.getSparkContext());
            tracker.submitJob(job);
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
        TransformResponse response;

        try {
            response = job.get(500, TimeUnit.MILLISECONDS);
            tracker.removeJob(table);
        } catch (ExecutionException cause) {
            ScriptException e = new ScriptException(cause);
            log.error("Throwing {}", e);
            throw e;
        } catch (Exception cause) {
            response = new TransformResponse();
            response.setProgress(0.0);
            response.setStatus(TransformResponse.Status.PENDING);
            response.setTable(table);
        }

        log.trace("exit with({})", response);
        return response;
    }