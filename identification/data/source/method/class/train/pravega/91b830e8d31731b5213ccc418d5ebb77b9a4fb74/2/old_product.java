@Override
    public void updateStream(final String scopeName, final String streamName,
            final UpdateStreamRequest updateStreamRequest, final SecurityContext securityContext,
            final AsyncResponse asyncResponse) {
        long traceId = LoggerHelpers.traceEnter(log, "updateStream");

        StreamConfiguration streamConfiguration = ModelHelper.getUpdateStreamConfig(
                updateStreamRequest, scopeName, streamName);
        controllerService.alterStream(streamConfiguration).thenApply(streamStatus -> {
            if (streamStatus.getStatus() == UpdateStreamStatus.Status.SUCCESS) {
                log.info("Successfully updated stream config for: {}/{}", scopeName, streamName);
                return Response.status(Status.OK)
                         .entity(ModelHelper.encodeStreamResponse(streamConfiguration)).build();
            } else if (streamStatus.getStatus() == UpdateStreamStatus.Status.STREAM_NOT_FOUND ||
                    streamStatus.getStatus() == UpdateStreamStatus.Status.SCOPE_NOT_FOUND) {
                log.warn("Stream: {}/{} not found", scopeName, streamName);
                return Response.status(Status.NOT_FOUND).build();
            } else {
                log.warn("updateStream failed for {}/{}", scopeName, streamName);
                return Response.status(Status.INTERNAL_SERVER_ERROR).build();
            }
        }).exceptionally(exception -> {
            log.warn("updateStream for {}/{} failed with exception: {}", scopeName, streamName, exception);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }).thenApply(asyncResponse::resume);

        LoggerHelpers.traceLeave(log, "updateStream", traceId);
    }