@Override
    public void deleteStream(final String scopeName, final String streamName, final SecurityContext securityContext,
            final AsyncResponse asyncResponse) {
        long traceId = LoggerHelpers.traceEnter(log, "deleteStream");

        try {
            authenticate(scopeName + "/" + streamName, READ_UPDATE);
        } catch (AuthenticationException e) {
            log.warn("Delete stream for {} failed due to authentication failure.", streamName);
            asyncResponse.resume(Response.status(Status.UNAUTHORIZED).build());
            LoggerHelpers.traceLeave(log, "deleteStream", traceId);
            return;
        }

        controllerService.deleteStream(scopeName, streamName).thenApply(deleteStreamStatus -> {
          if (deleteStreamStatus.getStatus() == DeleteStreamStatus.Status.SUCCESS) {
              log.info("Successfully deleted stream: {}", streamName);
              return Response.status(Status.NO_CONTENT).build();
          } else if (deleteStreamStatus.getStatus() == DeleteStreamStatus.Status.STREAM_NOT_FOUND) {
              log.warn("Scope: {}, Stream {} not found", scopeName, streamName);
              return Response.status(Status.NOT_FOUND).build();
          } else if (deleteStreamStatus.getStatus() == DeleteStreamStatus.Status.STREAM_NOT_SEALED) {
              log.warn("Cannot delete unsealed stream: {}", streamName);
              return Response.status(Status.PRECONDITION_FAILED).build();
          } else {
              log.warn("deleteStream for {} failed", streamName);
              return Response.status(Status.INTERNAL_SERVER_ERROR).build();
          }
        }).exceptionally(exception -> {
           log.warn("deleteStream for {} failed with exception: {}", streamName, exception);
           return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }).thenApply(asyncResponse::resume)
        .thenAccept(x -> LoggerHelpers.traceLeave(log, "deleteStream", traceId));
    }