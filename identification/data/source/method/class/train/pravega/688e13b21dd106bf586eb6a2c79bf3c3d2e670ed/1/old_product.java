@Override
    public void listScopes(final SecurityContext securityContext, final AsyncResponse asyncResponse) {
        long traceId = LoggerHelpers.traceEnter(log, "listScopes");

        Principal principal;
        try {
            principal = authenticate();
            authorize("/", principal, READ);
        } catch (AuthException e) {
            log.warn("Get scopes failed due to authentication failure.", e);
            asyncResponse.resume(Response.status(Status.fromStatusCode(e.getResponseCode())).build());
            LoggerHelpers.traceLeave(log, "listScopes", traceId);
            return;
        }

        controllerService.listScopes()
                         .thenApply(scopesList -> {
                             ScopesList scopes = new ScopesList();
                             scopesList.forEach(scope -> {
                                     scopes.addScopesItem(new ScopeProperty().scopeName(scope));
                             });
                             return Response.status(Status.OK).entity(scopes).build(); })
                         .exceptionally(exception -> {
                             log.warn("listScopes failed with exception: ", exception);
                             return Response.status(Status.INTERNAL_SERVER_ERROR).build(); })
                         .thenApply(response -> {
                             asyncResponse.resume(response);
                             LoggerHelpers.traceLeave(log, "listScopes", traceId);
                             return response;
                         });
    }