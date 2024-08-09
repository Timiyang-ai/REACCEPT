@Override
    public void createStream(final String scopeName, final CreateStreamRequest createStreamRequest,
            final SecurityContext securityContext, final AsyncResponse asyncResponse) {
        long traceId = LoggerHelpers.traceEnter(log, "createStream");

        StreamConfiguration streamConfiguration = ModelHelper.getCreateStreamConfig(createStreamRequest, scopeName);
        controllerService.createStream(streamConfiguration, System.currentTimeMillis())
                .thenApply(streamStatus -> {
                    if (streamStatus == CreateStreamStatus.SUCCESS) {
                        log.info("Successfully created stream: {}/{}", scopeName, streamConfiguration.getStreamName());
                        return Response.status(Status.CREATED).
                                entity(ModelHelper.encodeStreamResponse(streamConfiguration)).build();
                    } else if (streamStatus == CreateStreamStatus.STREAM_EXISTS) {
                        log.warn("Stream already exists: {}/{}", scopeName, streamConfiguration.getStreamName());
                        return Response.status(Status.CONFLICT).build();
                    } else if (streamStatus == CreateStreamStatus.SCOPE_NOT_FOUND) {
                        return Response.status(Status.NOT_FOUND).build();
                    } else {
                        log.warn("createStream failed for : {}/{}", scopeName, streamConfiguration.getStreamName());
                        return Response.status(Status.INTERNAL_SERVER_ERROR).build();
                    }
                }).exceptionally(exception -> {
                    log.warn("createStream for {}/{} failed {}: ", scopeName, streamConfiguration.getStreamName(),
                             exception);
                    return Response.status(Status.INTERNAL_SERVER_ERROR).build();
                }).thenApply(asyncResponse::resume);

        LoggerHelpers.traceLeave(log, "createStream", traceId);
    }