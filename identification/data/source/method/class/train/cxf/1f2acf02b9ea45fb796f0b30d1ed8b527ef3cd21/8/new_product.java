public static Token getAccessToken(WebClient accessTokenService,
                                       Consumer consumer,
                                       Token requestToken,
                                       String verifier) throws OAuthServiceException {
        return getAccessToken(accessTokenService, consumer, requestToken, verifier, null);
    }