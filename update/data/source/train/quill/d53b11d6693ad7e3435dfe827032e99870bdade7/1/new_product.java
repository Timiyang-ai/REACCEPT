private void refreshToken(String accessToken, String refreshToken, String authCode) {
        Retrofit retrofit = GhostApiUtils.getRetrofit(BLOG_URL, Helpers.getProdHttpClient());
        MockRetrofit mockRetrofit = Helpers.getMockRetrofit(retrofit, Helpers.getIdealNetworkBehavior());
        BehaviorDelegate<GhostApiService> delegate = mockRetrofit.create(GhostApiService.class);
        GhostApiService api = new MockGhostApiService(delegate, true);

        when(credSource.getGhostAuthCode(any())).thenReturn(Observable.just(authCode));

        AuthToken token = new AuthToken();
        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);

        AuthService authService = new AuthService(BLOG_URL, api, credSource, credSink);
        authService.listen(listener);
        authService.refreshToken(token);
    }