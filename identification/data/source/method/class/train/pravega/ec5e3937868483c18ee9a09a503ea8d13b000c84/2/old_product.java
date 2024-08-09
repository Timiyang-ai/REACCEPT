@Override
    public void getStream(final String scope, final String stream, final SecurityContext securityContext,
            final AsyncResponse asyncResponse) {
        long traceId = LoggerHelpers.traceEnter(log, "getStreamConfig");

        StreamMetadataStore streamStore = controllerService.getStreamStore();
        streamStore.getConfiguration(stream)
                .thenApply(streamConfig -> {
                    return Response.status(Status.OK).entity(ModelHelper.encodeStreamResponse(streamConfig)).build();
                })
                .exceptionally(exception -> {
                    if (exception.getCause() instanceof DataNotFoundException || exception instanceof DataNotFoundException) {
                        return Response.status(Status.NOT_FOUND).build();
                    } else {
                        log.debug("Exception occurred while executing getStreamConfig: " + exception);
                        return Response.status(Status.INTERNAL_SERVER_ERROR).build();
                    }
                }).thenApply(response -> asyncResponse.resume(response));

        LoggerHelpers.traceLeave(log, "getStreamConfig", traceId);
    }