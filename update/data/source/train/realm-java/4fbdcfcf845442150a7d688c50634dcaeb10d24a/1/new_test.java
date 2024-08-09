@Test
    public void login_withAccessToken() {
        RealmObjectServer authServer = Mockito.mock(RealmObjectServer.class);
        when(authServer.loginUser(any(SyncCredentials.class), any(URL.class))).thenThrow(new AssertionError("Server contacted."));
        RealmObjectServer originalServer = SyncManager.getAuthServer();
        SyncManager.setAuthServerImpl(authServer);
        try {
            SyncCredentials credentials = SyncCredentials.accessToken("foo", "bar");
            SyncUser user = SyncUser.logIn(credentials, "http://ros.realm.io/auth");
            assertTrue(user.isValid());
        } finally {
            SyncManager.setAuthServerImpl(originalServer);
        }
    }