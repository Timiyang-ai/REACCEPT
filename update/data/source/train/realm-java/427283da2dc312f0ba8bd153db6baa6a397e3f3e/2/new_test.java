@Ignore("This test fails because of wrong JSON string.")
    @Test
    public void currentUser_returnsUserAfterLogin() {
        AuthenticationServer authServer = Mockito.mock(AuthenticationServer.class);
        when(authServer.loginUser(any(SyncCredentials.class), any(URL.class))).thenReturn(SyncTestUtils.createLoginResponse(Long.MAX_VALUE));

        SyncUser user = SyncUser.logIn(SyncCredentials.facebook("foo"), "http://bar.com/auth");
        assertEquals(user, SyncUser.current());
    }