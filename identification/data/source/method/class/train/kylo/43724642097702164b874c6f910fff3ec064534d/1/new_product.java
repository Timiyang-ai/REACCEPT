@Nonnull
    public TransformResponse execute(@Nonnull final TransformRequest request) throws ScriptException {
        log.trace("entry params({})", request);

        // Build bindings list
        final List<NamedParam> bindings = new ArrayList<>();
        bindings.add(new NamedParamClass("sparkContextService", SparkContextService.class.getName(), sparkContextService));

        if (request.getDatasources() != null && !request.getDatasources().isEmpty()) {
            if (datasourceProviderFactory != null) {
                final DatasourceProvider datasourceProvider = datasourceProviderFactory.getDatasourceProvider(request.getDatasources());
                bindings.add(new NamedParamClass("datasourceProvider", DatasourceProvider.class.getName() + "[org.apache.spark.sql.DataFrame]", datasourceProvider));
            } else {
                final ScriptException e = new ScriptException("Script cannot be executed because no data source provider factory is available.");
                log.error("Throwing {}", e);
                throw e;
            }
        }

        // Execute script
        final Object result;
        try {
            result = this.engine.eval(toScript(request), bindings);
        } catch (final Exception cause) {
            final ScriptException e = new ScriptException(cause);
            log.error("Throwing {}", e);
            throw e;
        }

        TransformResponse response;
        StructType schema;
        if (result instanceof DataSet) {
            final DataSet dataSet = (DataSet) result;
            schema = dataSet.schema();
            response = submitJob(new ShellTransformStage(dataSet), getPolicies(request));
        } else {
            final IllegalStateException e = new IllegalStateException("Unexpected script result type: " + (result != null ? result.getClass() : null));
            log.error("Throwing {}", e);
            throw e;
        }

        // Build response
        if (response.getStatus() != TransformResponse.Status.SUCCESS) {
            final String table = response.getTable();
            final TransformQueryResult partialResult = new TransformQueryResult();
            partialResult.setColumns(Arrays.<QueryResultColumn>asList(new QueryResultRowTransform(schema, table).columns()));

            response = new TransformResponse();
            response.setProgress(0.0);
            response.setResults(partialResult);
            response.setStatus(TransformResponse.Status.PENDING);
            response.setTable(table);
        }

        log.trace("exit with({})", response);
        return response;
    }