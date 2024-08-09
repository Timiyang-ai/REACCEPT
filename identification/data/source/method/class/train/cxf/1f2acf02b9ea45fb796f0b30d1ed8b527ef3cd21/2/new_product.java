public static Token getAccessToken(WebClient accessTokenService,
                                       Consumer consumer,
                                       Token requestToken,
                                       String verifier) throws OAuthServiceException {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put(OAuth.OAUTH_CONSUMER_KEY, consumer.getKey());
        parameters.put(OAuth.OAUTH_TOKEN, requestToken.getToken());
        parameters.put(OAuth.OAUTH_VERIFIER, verifier);
        parameters.put(OAuth.OAUTH_SIGNATURE_METHOD, "HMAC-SHA1");
        
        OAuthConsumer oAuthConsumer = new OAuthConsumer(null, consumer.getKey(), 
                consumer.getSecret(), null);
        OAuthAccessor accessor = new OAuthAccessor(oAuthConsumer);
        accessor.requestToken = requestToken.getToken();
        accessor.tokenSecret = requestToken.getSecret();
        return getToken(accessTokenService, accessor, parameters);
    }