@Override
    public void updateStream(final String scope, final String stream, final UpdateStreamRequest updateStreamRequest,
            final SecurityContext securityContext, final AsyncResponse asyncResponse) {
        long traceId = LoggerHelpers.traceEnter(log, "updateStreamConfig");

        StreamConfiguration streamConfiguration = ModelHelper.getUpdateStreamConfig(updateStreamRequest, scope, stream);
        CompletableFuture<UpdateStreamStatus> updateStreamStatus = controllerService.alterStream(streamConfiguration);

        updateStreamStatus.thenApply(streamStatus -> {
                    if (streamStatus == UpdateStreamStatus.SUCCESS) {
                        return Response.status(Status.CREATED).
                                entity(ModelHelper.encodeStreamResponse(streamConfiguration)).build();
                    } else if (streamStatus == UpdateStreamStatus.STREAM_NOT_FOUND || streamStatus == UpdateStreamStatus.SCOPE_NOT_FOUND) {
                        return Response.status(Status.NOT_FOUND).build();
                    } else {
                        return Response.status(Status.INTERNAL_SERVER_ERROR).build();
                    }
                }
        ).exceptionally(exception -> {
            log.debug("Exception occurred while executing updateStreamConfig: " + exception);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }).thenApply(response -> asyncResponse.resume(response));

        LoggerHelpers.traceLeave(log, "updateStreamConfig", traceId);
    }