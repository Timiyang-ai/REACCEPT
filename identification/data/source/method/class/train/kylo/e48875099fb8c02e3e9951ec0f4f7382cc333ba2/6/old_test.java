    @Test
    public void onLoginSuccess() throws Exception {
        // Mock request, response, and authentication
        final AtomicReference<Cookie> cookie = new AtomicReference<>();
        final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        Mockito.doAnswer(answer -> cookie.compareAndSet(null, answer.getArgumentAt(0, Cookie.class)))
            .when(response).addCookie(Mockito.any());

        final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getContextPath()).thenReturn("");

        final Authentication auth = new UsernamePasswordAuthenticationToken("dladmin", "thinkbig", groupAuthorities("admin"));

        // Test cookie set by login
        service.onLoginSuccess(request, response, auth);
        Assert.assertEquals("eyJhbGciOiJIUzI1NiIsImtpZCI6IkhNQUMifQ.eyJleHAiOjE0NjMxNTE5MDAsInN1YiI6ImRsYWRtaW4iLCJwcmluY2lwYWxzIjpbIntcImNvbS50aGlua2J"
                        + "pZ2FuYWx5dGljcy5zZWN1cml0eS5Hcm91cFByaW5jaXBhbFwiOltcImFkbWluXCJdfSJdfQ.DH4pxE8eWCmqPlhFMiEAbBja5k833gg0guE6m8DXvIA",
                            cookie.get().getValue());
    }