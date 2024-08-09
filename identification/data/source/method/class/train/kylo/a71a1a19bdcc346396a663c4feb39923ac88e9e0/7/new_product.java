@Nonnull
    public TransformResponse execute(@Nonnull final TransformRequest request) throws ScriptException {
        log.entry(request);

        // Handle async request
        if (request.isAsync()) {
            return cacheTransform(request);
        }

        // Execute script
        final DataSet dataSet = createShellTask(request);
        final StructType schema = dataSet.schema();
        TransformResponse response = submitTransformJob(new ShellTransformStage(dataSet, converterService), getPolicies(request), request.getPageSpec());

        // Build response
        if (response.getStatus() != TransformResponse.Status.SUCCESS) {
            final String table = response.getTable();
            final TransformQueryResult partialResult = new TransformQueryResult();
            partialResult.setColumns(Arrays.<QueryResultColumn>asList(new QueryResultRowTransform(schema, table, converterService).columns()));

            response = new TransformResponse();
            response.setProgress(0.0);
            response.setResults(partialResult);
            response.setStatus(TransformResponse.Status.PENDING);
            response.setTable(table);
        }

        return log.exit(response);
    }