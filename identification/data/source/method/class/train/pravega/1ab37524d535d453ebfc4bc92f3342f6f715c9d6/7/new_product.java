@Override
    public void createScope(final CreateScopeRequest createScopeRequest, final SecurityContext securityContext,
                            final AsyncResponse asyncResponse) {
        long traceId = LoggerHelpers.traceEnter(log, "createScope");
        try {
            NameUtils.validateUserScopeName(createScopeRequest.getScopeName());
        } catch (IllegalArgumentException | NullPointerException e) {
            log.warn("Create scope failed due to invalid scope name {}", createScopeRequest.getScopeName());
            asyncResponse.resume(Response.status(Status.BAD_REQUEST).build());
            LoggerHelpers.traceLeave(log, "createScope", traceId);
            return;
        }

        try {
            authenticate(createScopeRequest.getScopeName(), READ_UPDATE);
        } catch (AuthenticationException e) {
            log.warn("Create scope for {} failed due to authentication failure {}.", createScopeRequest.getScopeName(), e);
            asyncResponse.resume(Response.status(Status.UNAUTHORIZED).build());
            LoggerHelpers.traceLeave(log, "createScope", traceId);
            return;
        }

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