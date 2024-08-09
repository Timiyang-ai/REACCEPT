    @Test
    public void logUserIn() throws Exception {

        try {
            authenticationResource.logUserIn(AUTHHEADER, "");
            fail();
        } catch (AuthenticationException ignored) {
        }

        LoginToken loginToken = LoginToken.withAccessToken("access").build();
        when(authentication.logIn(AUTHHEADER)).thenReturn(loginToken);
        Response response = authenticationResource.logUserIn(AUTHHEADER, "client_credentials");
        assert (loginToken.equals(response.getEntity()));
    }