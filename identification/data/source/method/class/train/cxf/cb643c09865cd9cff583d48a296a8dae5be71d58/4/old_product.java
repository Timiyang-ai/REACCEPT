public static URI getAuthorizationURI(String authorizationServiceURI, 
                                          String clientId,
                                          String redirectUri,
                                          String state,
                                          String scope) {
        UriBuilder ub = getAuthorizationURIBuilder(authorizationServiceURI, 
                                                   clientId,
                                                   scope);
        if (redirectUri != null) {
            ub.queryParam("redirect_uri", redirectUri);
        }
        if (state != null) {
            ub.queryParam("state", state);
        }
        return ub.build();
    }