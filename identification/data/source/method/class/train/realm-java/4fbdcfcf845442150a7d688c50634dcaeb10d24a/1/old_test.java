@Test
    public void login_withAccessToken() {
        AuthenticationServer authServer = Mockito.mock(AuthenticationServer.class);
        when(authServer.loginUser(any(SyncCredentials.class), any(URL.class))).thenThrow(new AssertionError("Server contacted."));
        AuthenticationServer originalServer = SyncManager.getAuthServer();
        SyncManager.setAuthServerImpl(authServer);
        try {
            SyncCredentials credentials = SyncCredentials.accessToken("foo", "bar");
            SyncUser user = SyncUser.logIn(credentials, "http://ros.realm.io/auth");
            assertTrue(user.isValid());
        } finally {
            SyncManager.setAuthServerImpl(originalServer);
        }
    }