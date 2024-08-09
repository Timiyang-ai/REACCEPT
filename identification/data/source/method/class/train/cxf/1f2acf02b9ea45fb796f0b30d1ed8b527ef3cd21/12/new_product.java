public static ClientAccessToken getAccessToken(WebClient accessTokenService,
                                                   Consumer consumer,
                                                   AccessTokenGrant grant,
                                                   Map<String, String> extraParams,
                                                   boolean setAuthorizationHeader) 
        throws OAuthServiceException {
        return getAccessToken(accessTokenService, consumer, grant, extraParams, 
                              null, setAuthorizationHeader);
    }