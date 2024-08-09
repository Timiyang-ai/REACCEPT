public static ClientAccessToken getAccessToken(WebClient accessTokenService,
                                                   Consumer consumer,
                                                   AccessTokenGrant grant,
                                                   boolean setAuthorizationHeader) 
        throws OAuthServiceException {
        
        Form form = new Form(grant.toMap());
        
        if (setAuthorizationHeader) {
            StringBuilder sb = new StringBuilder();
            sb.append("Basic ");
            try {
                String data = consumer.getKey() + ":" + consumer.getSecret();
                sb.append(Base64Utility.encode(data.getBytes("UTF-8")));
            } catch (Exception ex) {
                throw new ClientWebApplicationException(ex);
            }
            accessTokenService.header("Authorization", sb.toString());
        } else {
            form.set(OAuthConstants.CLIENT_ID, consumer.getKey());
            form.set(OAuthConstants.CLIENT_SECRET, consumer.getSecret());
        }
        Response response = accessTokenService.form(form);
        Map<String, String> map = null;
        try {
            map = new OAuthJSONProvider().readJSONResponse((InputStream)response.getEntity());
        } catch (IOException ex) {
            throw new ClientWebApplicationException(ex);
        }
        if (200 == response.getStatus()) {
            if (map.containsKey(OAuthConstants.ACCESS_TOKEN)
                && map.containsKey(OAuthConstants.ACCESS_TOKEN_TYPE)) {
                String tokenType = map.get(OAuthConstants.ACCESS_TOKEN_TYPE);
                
                ClientAccessToken token = new ClientAccessToken(
                                              tokenType,
                                              map.get(OAuthConstants.ACCESS_TOKEN));
                return token;
            } else {
                throw new OAuthServiceException(OAuthConstants.SERVER_ERROR);
            }
        } else if (400 == response.getStatus() && map.containsValue(OAuthConstants.ERROR_KEY)) {
            OAuthError error = new OAuthError(map.get(OAuthConstants.ERROR_KEY),
                                              map.get(OAuthConstants.ERROR_DESCRIPTION_KEY));
            throw new OAuthServiceException(error);
        } 
        throw new OAuthServiceException(OAuthConstants.SERVER_ERROR);
    }