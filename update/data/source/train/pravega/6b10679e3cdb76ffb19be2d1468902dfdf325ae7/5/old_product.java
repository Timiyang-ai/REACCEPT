@Override
    public void createStream(final String scope, final CreateStreamRequest createStreamRequest,
            final SecurityContext securityContext, final AsyncResponse asyncResponse) {
        long traceId = LoggerHelpers.traceEnter(log, "createStream");

        StreamConfiguration streamConfiguration = ModelHelper.getCreateStreamConfig(createStreamRequest, scope);
        CompletableFuture<CreateStreamStatus> createStreamStatus = controllerService.createStream(streamConfiguration,
                System.currentTimeMillis());

        createStreamStatus.thenApply(streamStatus -> {
                    if (streamStatus == CreateStreamStatus.SUCCESS) {
                        return Response.status(Status.CREATED).
                                entity(ModelHelper.encodeStreamResponse(streamConfiguration)).build();
                    } else if (streamStatus == CreateStreamStatus.STREAM_EXISTS) {
                        return Response.status(Status.CONFLICT).build();
                    } else if (streamStatus == CreateStreamStatus.SCOPE_NOT_FOUND) {
                        return Response.status(Status.NOT_FOUND).build();
                    } else {
                        return Response.status(Status.INTERNAL_SERVER_ERROR).build();
                    }
                }
        ).exceptionally(exception -> {
            log.debug("Exception occurred while executing createStream: " + exception);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }).thenApply(response -> asyncResponse.resume(response));

        LoggerHelpers.traceLeave(log, "createStream", traceId);
    }