@Nonnull
    public TransformResponse execute(@Nonnull final TransformRequest request) throws ScriptException {
        log.trace("entry params({})", request);

        // Generate destination
        final String table = newTableName();

        // Build bindings list
        final List<NamedParam> bindings = new ArrayList<>();
        bindings.add(new NamedParamClass("profiler", Profiler.class.getName(), profiler));
        bindings.add(new NamedParamClass("sparkContextService", SparkContextService.class.getName(), sparkContextService));
        bindings.add(new NamedParamClass("tableName", "String", table));

        if (request.getDatasources() != null && !request.getDatasources().isEmpty()) {
            if (datasourceProviderFactory != null) {
                final DatasourceProvider datasourceProvider = datasourceProviderFactory.getDatasourceProvider(request.getDatasources());
                bindings.add(new NamedParamClass("datasourceProvider", DatasourceProvider.class.getName(), datasourceProvider));
            } else {
                final ScriptException e = new ScriptException("Script cannot be executed because no data source provider factory is available.");
                log.error("Throwing {}", e);
                throw e;
            }
        }

        // Execute script
        final Object result = this.engine.eval(toScript(request), bindings);

        final TransformJob job;
        if (result instanceof Callable) {
            @SuppressWarnings("unchecked") final Callable<TransformResponse> callable = (Callable) result;
            job = new TransformJob(table, callable, engine.getSparkContext());
            tracker.submitJob(job);
        } else {
            final IllegalStateException e = new IllegalStateException("Unexpected script result type: " + (result != null ? result.getClass() : null));
            log.error("Throwing {}", e);
            throw e;
        }

        // Build response
        TransformResponse response;

        try {
            response = job.get(500, TimeUnit.MILLISECONDS);
            tracker.removeJob(table);
        } catch (final ExecutionException cause) {
            final ScriptException e = new ScriptException(cause);
            log.error("Throwing {}", e);
            throw e;
        } catch (final InterruptedException | TimeoutException e) {
            log.trace("Timeout waiting for script result", e);
            response = new TransformResponse();
            response.setProgress(0.0);
            response.setStatus(TransformResponse.Status.PENDING);
            response.setTable(table);
        }

        log.trace("exit with({})", response);
        return response;
    }