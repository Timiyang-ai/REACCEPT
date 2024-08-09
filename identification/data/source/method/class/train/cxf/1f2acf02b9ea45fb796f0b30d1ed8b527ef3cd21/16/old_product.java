public static ClientAccessToken getAccessToken(WebClient accessTokenService,
                                                   Consumer consumer,
                                                   AccessTokenGrant grant,
                                                   boolean setAuthorizationHeader) 
        throws OAuthServiceException {
        return getAccessToken(accessTokenService, consumer, grant, extraParams, 
                              null, setAuthorizationHeader);
    }