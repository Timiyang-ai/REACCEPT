@Override
    public void createScope(final CreateScopeRequest createScopeRequest, final SecurityContext securityContext,
            final AsyncResponse asyncResponse) {
        long traceId = LoggerHelpers.traceEnter(log, "createScope");

        controllerService.createScope(createScopeRequest.getScopeName()).thenApply(scopeStatus -> {
            if (scopeStatus.getStatus() == CreateScopeStatus.Status.SUCCESS) {
                log.info("Successfully created new scope: {}", createScopeRequest.getScopeName());
                return Response.status(Status.CREATED).
                        entity(new ScopeProperty().scopeName(createScopeRequest.getScopeName())).build();
            } else if (scopeStatus.getStatus() == CreateScopeStatus.Status.SCOPE_EXISTS) {
                log.warn("Scope name: {} already exists", createScopeRequest.getScopeName());
                return Response.status(Status.CONFLICT).build();
            } else {
                log.warn("Failed to create scope: {}", createScopeRequest.getScopeName());
                return Response.status(Status.INTERNAL_SERVER_ERROR).build();
            }
        }).exceptionally(exception -> {
            log.warn("createScope for scope: {} failed, exception: {}", createScopeRequest.getScopeName(), exception);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }).thenApply(asyncResponse::resume)
        .thenAccept(x -> LoggerHelpers.traceLeave(log, "createScope", traceId));
    }