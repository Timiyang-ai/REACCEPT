public static URI getAuthorizationURI(String authorizationServiceURI,
                                          String clientId,
                                          String redirectUri,
                                          String state,
                                          String scope) {
        UriBuilder ub = getAuthorizationURIBuilder(authorizationServiceURI,
                                                   clientId,
                                                   redirectUri,
                                                   state,
                                                   scope);
        return ub.build();
    }