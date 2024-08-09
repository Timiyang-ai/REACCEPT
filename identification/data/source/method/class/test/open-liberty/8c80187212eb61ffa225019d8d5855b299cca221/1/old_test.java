@Test
    public void authenticate_WithCookie() throws Exception {
        final Cookie[] cookieArray = { cookie };
        final Subject authSubject = new Subject();

        mock.checking(new Expectations() {
            {
                allowing(req).getCookies();
                will(returnValue(cookieArray));

                allowing(webAppSecConfig).getLogoutOnHttpSessionExpire();
                will(returnValue(false));

                allowing(webAppSecConfig).isTrackLoggedOutSSOCookiesEnabled();
                will(returnValue(false));

                allowing(ssoCookieHelper).getSSOCookiename();
                will(returnValue("LTPAToken2"));

                one(webAppSecConfig).isUseOnlyCustomCookieName();
                will(returnValue(false));

                // Now authenticate, which should be successful and the result
                // immediately returned
                one(authService).authenticate(with(equal(JaasLoginConfigConstants.SYSTEM_WEB_INBOUND)), with(any(AuthenticationData.class)), with(equal((Subject) null)));
                will(returnValue(authSubject));
            }
        });

        AuthenticationResult authResult = ssoAuth.authenticate(webRequest, webAppSecConfig);
        assertEquals("AuthenticationResult should be SUCCESS",
                     AuthResult.SUCCESS, authResult.getStatus());
    }