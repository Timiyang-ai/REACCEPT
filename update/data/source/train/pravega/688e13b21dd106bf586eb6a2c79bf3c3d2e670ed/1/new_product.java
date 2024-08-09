@Override
    public void listScopes(final SecurityContext securityContext, final AsyncResponse asyncResponse) {
        long traceId = LoggerHelpers.traceEnter(log, "listScopes");

        final Principal principal;
        final List<String> authHeader = getAuthorizationHeader();

        try {
            principal = restAuthHelper.authenticate(authHeader);
            restAuthHelper.authorize(authHeader, AuthResourceRepresentation.ofScopes(), principal, READ);
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
                                 try {
                                     if (restAuthHelper.isAuthorized(authHeader,
                                             AuthResourceRepresentation.ofScope(scope),
                                             principal, READ)) {
                                         scopes.addScopesItem(new ScopeProperty().scopeName(scope));
                                     }
                                 } catch (AuthException e) {
                                     log.warn(e.getMessage(), e);
                                     // Ignore. This exception occurs under abnormal circumstances and not to determine
                                     // whether the user is authorized. In case it does occur, we assume that the user
                                     // is unauthorized.
                                 }
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