public static ClientAccessToken getAccessToken(String accessTokenServiceUri,
                                                   Consumer consumer,
                                                   AccessTokenGrant grant,
                                                   boolean setAuthorizationHeader) 
        throws OAuthServiceException {
        OAuthJSONProvider provider = new OAuthJSONProvider();
        WebClient accessTokenService = 
            WebClient.create(accessTokenServiceUri, Collections.singletonList(provider));
        accessTokenService.accept("application/json");
        return getAccessToken(accessTokenService, consumer, grant, true);
    }