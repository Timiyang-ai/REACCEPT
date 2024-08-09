public static URI getAuthorizationURI(String authorizationServiceURI, 
                                          String clientId,
                                          String redirectUri,
                                          String state,
                                          String scope) {
        return getAuthorizationURIBuilder(authorizationServiceURI, 
                                                   clientId,
                                                   redirectUri,
                                                   state,
                                                   scope).build();
    }