@Override
    public void deleteScope(final String scopeName, final SecurityContext securityContext,
            final AsyncResponse asyncResponse) {
        long traceId = LoggerHelpers.traceEnter(log, "deleteScope");

        controllerService.deleteScope(scopeName).thenApply(scopeStatus -> {
            if (scopeStatus.getStatus() == DeleteScopeStatus.Status.SUCCESS) {
                log.info("Successfully deleted scope: {}", scopeName);
                return Response.status(Status.NO_CONTENT).build();
            } else if (scopeStatus.getStatus() == DeleteScopeStatus.Status.SCOPE_NOT_FOUND) {
                log.warn("Scope: {} not found", scopeName);
                return Response.status(Status.NOT_FOUND).build();
            } else if (scopeStatus.getStatus() == DeleteScopeStatus.Status.SCOPE_NOT_EMPTY) {
                log.warn("Cannot delete scope: {} with non-empty streams", scopeName);
                return Response.status(Status.PRECONDITION_FAILED).build();
            } else {
                log.warn("deleteScope for {} failed", scopeName);
                return Response.status(Status.INTERNAL_SERVER_ERROR).build();
            }
        }).exceptionally(exception -> {
            log.warn("deleteScope for {} failed with exception: {}", scopeName, exception);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }).thenApply(asyncResponse::resume);

        LoggerHelpers.traceLeave(log, "deleteScope", traceId);
    }