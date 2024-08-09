public static ClientAccessToken getAccessToken(WebClient accessTokenService,
                                                   Consumer consumer,
                                                   AccessTokenGrant grant,
                                                   Map<String, String> extraParams,
                                                   String defaultTokenType,
                                                   boolean setAuthorizationHeader) 
        throws OAuthServiceException {    
        
        Form form = new Form(grant.toMap());
        if (extraParams != null) {
            for (Map.Entry<String, String> entry : extraParams.entrySet()) {
                form.param(entry.getKey(), entry.getValue());
            }
        }
        if (consumer != null) {
            if (setAuthorizationHeader) {
                StringBuilder sb = new StringBuilder();
                sb.append("Basic ");
                try {
                    String data = consumer.getKey() + ":" + consumer.getSecret();
                    sb.append(Base64Utility.encode(data.getBytes("UTF-8")));
                } catch (Exception ex) {
                    throw new ProcessingException(ex);
                }
                accessTokenService.header("Authorization", sb.toString());
            } else {
                form.param(OAuthConstants.CLIENT_ID, consumer.getKey());
                if (consumer.getSecret() != null) {
                    form.param(OAuthConstants.CLIENT_SECRET, consumer.getSecret());
                }
            }
        } else {
            // in this case the AccessToken service is expected to find a mapping between
            // the authenticated credentials and the client registration id
        }
        Response response = accessTokenService.form(form);
        Map<String, String> map = null;
        try {
            map = new OAuthJSONProvider().readJSONResponse((InputStream)response.getEntity());
        } catch (IOException ex) {
            throw new ResponseProcessingException(response, ex);
        }
        if (200 == response.getStatus()) {
            ClientAccessToken token = fromMapToClientToken(map, defaultTokenType);
            if (token == null) {
                throw new OAuthServiceException(OAuthConstants.SERVER_ERROR);
            } else {
                return token;
            }
        } else if (400 == response.getStatus() && map.containsKey(OAuthConstants.ERROR_KEY)) {
            OAuthError error = new OAuthError(map.get(OAuthConstants.ERROR_KEY),
                                              map.get(OAuthConstants.ERROR_DESCRIPTION_KEY));
            error.setErrorUri(map.get(OAuthConstants.ERROR_URI_KEY));
            throw new OAuthServiceException(error);
        } 
        throw new OAuthServiceException(OAuthConstants.SERVER_ERROR);
    }