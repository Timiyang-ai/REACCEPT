@Override
    public void getStream(final String scopeName, final String streamName, final SecurityContext securityContext,
            final AsyncResponse asyncResponse) {
        long traceId = LoggerHelpers.traceEnter(log, "getStream");

        try {
            authenticate(scopeName + "/" + streamName, READ);
        } catch (AuthenticationException e) {
            log.warn("Get stream for {} failed due to authentication failure.", scopeName + "/" + streamName);
            asyncResponse.resume(Response.status(Status.UNAUTHORIZED).build());
            LoggerHelpers.traceLeave(log, "getStream", traceId);
            return;
        }

        controllerService.getStream(scopeName, streamName)
                .thenApply(streamConfig -> Response.status(Status.OK)
                        .entity(ModelHelper.encodeStreamResponse(streamConfig))
                        .build())
                .exceptionally(exception -> {
                    if (exception.getCause() instanceof StoreException.DataNotFoundException
                            || exception instanceof StoreException.DataNotFoundException) {
                        log.warn("Stream: {}/{} not found", scopeName, streamName);
                        return Response.status(Status.NOT_FOUND).build();
                    } else {
                        log.warn("getStream for {}/{} failed with exception: {}", scopeName, streamName, exception);
                        return Response.status(Status.INTERNAL_SERVER_ERROR).build();
                    }
                }).thenApply(asyncResponse::resume)
                .thenAccept(x ->  LoggerHelpers.traceLeave(log, "getStream", traceId));
    }