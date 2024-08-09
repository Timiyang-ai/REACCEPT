@Override
    public void updateStreamState(final String scopeName, final String streamName,
            final StreamState updateStreamStateRequest, SecurityContext securityContext, AsyncResponse asyncResponse) {
        long traceId = LoggerHelpers.traceEnter(log, "updateStreamState");

        // We only support sealed state now.
        if (updateStreamStateRequest.getStreamState() != StreamState.StreamStateEnum.SEALED) {
            log.warn("Received invalid stream state: {} from client for stream {}/{}",
                     updateStreamStateRequest.getStreamState(), scopeName, streamName);
            asyncResponse.resume(Response.status(Status.BAD_REQUEST).build());
            return;
        }

        controllerService.sealStream(scopeName, streamName).thenApply(updateStreamStatus  -> {
            if (updateStreamStatus.getStatus() == UpdateStreamStatus.Status.SUCCESS) {
                log.info("Successfully sealed stream: {}", streamName);
                return Response.status(Status.OK).entity(updateStreamStateRequest).build();
            } else if (updateStreamStatus.getStatus() == UpdateStreamStatus.Status.SCOPE_NOT_FOUND ||
                    updateStreamStatus.getStatus() == UpdateStreamStatus.Status.STREAM_NOT_FOUND) {
                log.warn("Scope: {} or Stream {} not found", scopeName, streamName);
                return Response.status(Status.NOT_FOUND).build();
            } else {
                log.warn("updateStreamState for {} failed", streamName);
                return Response.status(Status.INTERNAL_SERVER_ERROR).build();
            }
        }).exceptionally(exception -> {
            log.warn("updateStreamState for {} failed with exception: {}", streamName, exception);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }).thenApply(asyncResponse::resume)
        .thenAccept(x -> LoggerHelpers.traceLeave(log, "updateStreamState", traceId));
    }