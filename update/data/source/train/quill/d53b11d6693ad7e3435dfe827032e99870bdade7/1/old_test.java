@Test
    public void refreshToken_expiredAccessToken() {
        refreshToken("expired-access-token", "refresh-token", "auth-code");

        verify(credSink).setLoggedIn(true);
        verify(listener).onNewAuthToken(argThat(hasProperty("accessToken", is("refreshed-access-token"))));
        verify(listener).onNewAuthToken(argThat(hasProperty("refreshToken", is("refresh-token"))));
    }