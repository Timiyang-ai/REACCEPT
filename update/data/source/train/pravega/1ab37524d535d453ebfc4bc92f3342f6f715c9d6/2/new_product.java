@Override
    public void getScope(final String scopeName, final SecurityContext securityContext,
                         final AsyncResponse asyncResponse) {
        long traceId = LoggerHelpers.traceEnter(log, "getScope");

        try {
            authenticate(scopeName, READ);
        } catch (AuthenticationException e) {
            log.warn("Get scope for {} failed due to authentication failure.", scopeName);
            asyncResponse.resume(Response.status(Status.UNAUTHORIZED).build());
            LoggerHelpers.traceLeave(log, "getScope", traceId);
            return;
        }

        controllerService.getScope(scopeName)
                .thenApply(scope -> {
                        return Response.status(Status.OK).entity(new ScopeProperty().scopeName(scope)).build();
                })
                .exceptionally( exception -> {
                    if (exception.getCause() instanceof StoreException.DataNotFoundException) {
                        log.warn("Scope: {} not found", scopeName);
                        return Response.status(Status.NOT_FOUND).build();
                    } else {
                        log.warn("getScope for {} failed with exception: {}", scopeName, exception);
                        return Response.status(Status.INTERNAL_SERVER_ERROR).build();
                    }
                }).thenApply(asyncResponse::resume)
                .thenAccept(x -> LoggerHelpers.traceLeave(log, "getScope", traceId));
    }