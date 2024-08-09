    @Test
    public void getAuthorizationURI() {
        String authorizationServiceURI = "https://authorization";
        String clientId = "clientId";
        String redirectUri = "https://redirect";
        String state = "unique";
        String scope = OAuthConstants.REFRESH_TOKEN_SCOPE;

        URI uri = OAuthClientUtils.getAuthorizationURI(authorizationServiceURI, clientId, redirectUri, state, scope);

        assertTrue(uri.toString().startsWith(authorizationServiceURI));

        Map<String, String> query = Arrays.asList(uri.getQuery().split("&")).stream().map(s -> s.split("="))
                .collect(Collectors.toMap(a -> a[0], a -> a.length > 1 ? a[1] : ""));
        assertEquals(clientId, query.get(OAuthConstants.CLIENT_ID));
        assertEquals(redirectUri, query.get(OAuthConstants.REDIRECT_URI));
        assertEquals(state, query.get(OAuthConstants.STATE));
        assertEquals(OAuthConstants.CODE_RESPONSE_TYPE, query.get(OAuthConstants.RESPONSE_TYPE));
    }