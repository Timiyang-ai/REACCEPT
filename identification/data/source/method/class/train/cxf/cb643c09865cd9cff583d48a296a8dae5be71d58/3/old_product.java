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
        if (redirectUri != null) {
            ub.queryParam(OAuthConstants.REDIRECT_URI, redirectUri);
        }
        if (state != null) {
            ub.queryParam(OAuthConstants.STATE, state);
        }
        return ub.build();
    }