    @Test
    public void getAccessToken() {
        WebClient accessTokenService = mock(WebClient.class);
        String tokenKey = "tokenKey";
        String response = "{\"" + OAuthConstants.ACCESS_TOKEN + "\":\"" + tokenKey + "\"}";
        expect(accessTokenService.form(anyObject(Form.class))).andReturn(
                Response.ok(new ByteArrayInputStream(response.getBytes()), MediaType.APPLICATION_JSON).build());
        replay(accessTokenService);

        ClientAccessToken cat = OAuthClientUtils.getAccessToken(accessTokenService, null, new RefreshTokenGrant(""),
                null, "defaultTokenType", false);
        assertEquals(tokenKey, cat.getTokenKey());

        verify(accessTokenService);
    }