public static ClientAccessToken getAccessToken(WebClient accessTokenService,
                                                   Consumer consumer,
                                                   AccessTokenGrant grant,
                                                   boolean setAuthorizationHeader) {
        return getAccessToken(accessTokenService, consumer, grant, null, setAuthorizationHeader);
    }