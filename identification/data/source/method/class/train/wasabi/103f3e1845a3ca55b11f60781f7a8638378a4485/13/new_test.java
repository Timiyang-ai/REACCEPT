    @Test
    public void verifyToken() throws Exception {

        LoginToken loginToken = LoginToken.withAccessToken("access").build();
        when(authentication.verifyToken(TOKENHEADER)).thenReturn(loginToken);
        Response response = authenticationResource.verifyToken(TOKENHEADER);
        assert (loginToken.equals(response.getEntity()));
    }