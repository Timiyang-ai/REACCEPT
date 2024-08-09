public static ClientAccessToken getAccessToken(WebClient accessTokenService,
                                                   Consumer consumer,
                                                   AccessTokenGrant grant,
                                                   Map<String, String> extraParams,
                                                   String defaultTokenType,
                                                   boolean setAuthorizationHeader)
        throws OAuthServiceException {

        if (accessTokenService == null) {
            throw new OAuthServiceException(OAuthConstants.SERVER_ERROR);
        }

        Form form = new Form(grant.toMap());
        if (extraParams != null) {
            for (Map.Entry<String, String> entry : extraParams.entrySet()) {
                form.param(entry.getKey(), entry.getValue());
            }
        }
        if (consumer != null) {
            boolean secretAvailable = !StringUtils.isEmpty(consumer.getClientSecret());
            if (setAuthorizationHeader && secretAvailable) {
                try {
                    String data = Base64Utility.encode((consumer.getClientId() + ':' + consumer.getClientSecret())
                            .getBytes(StandardCharsets.UTF_8));
                    accessTokenService.replaceHeader(HttpHeaders.AUTHORIZATION, "Basic " + data);
                } catch (Exception ex) {
                    throw new ProcessingException(ex);
                }
            } else {
                form.param(OAuthConstants.CLIENT_ID, consumer.getClientId());
                if (secretAvailable) {
                    form.param(OAuthConstants.CLIENT_SECRET, consumer.getClientSecret());
                }
            }
        } else {
            // in this case the AccessToken service is expected to find a mapping between
            // the authenticated credentials and the client registration id
        }
        Response response = accessTokenService.form(form);
        final Map<String, String> map;
        try {
            map = response.getMediaType() == null
                    || response.getMediaType().isCompatible(MediaType.APPLICATION_JSON_TYPE)
                            ? new OAuthJSONProvider().readJSONResponse((InputStream) response.getEntity())
                            : Collections.emptyMap();
        } catch (Exception ex) {
            throw new ResponseProcessingException(response, ex);
        }
        if (200 == response.getStatus()) {
            ClientAccessToken token = fromMapToClientToken(map, defaultTokenType);
            if (token == null) {
                throw new OAuthServiceException(OAuthConstants.SERVER_ERROR);
            }
            return token;
        } else if (response.getStatus() >= 400 && map.containsKey(OAuthConstants.ERROR_KEY)) {
            OAuthError error = new OAuthError(map.get(OAuthConstants.ERROR_KEY),
                                              map.get(OAuthConstants.ERROR_DESCRIPTION_KEY));
            error.setErrorUri(map.get(OAuthConstants.ERROR_URI_KEY));
            throw new OAuthServiceException(error);
        }
        throw new OAuthServiceException(OAuthConstants.SERVER_ERROR);
    }