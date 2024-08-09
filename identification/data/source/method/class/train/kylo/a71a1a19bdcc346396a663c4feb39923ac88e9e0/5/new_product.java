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
        TransformResponse response = submitTransformJob(new ShellTransformStage(dataSet, converterService),request);
        updateTransformResponse(response,dataSet);
        return log.exit(response);
    }