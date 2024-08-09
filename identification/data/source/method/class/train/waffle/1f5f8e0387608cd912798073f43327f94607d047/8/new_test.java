    @Test
    public void login_invalidGuestLogin() throws LoginException {
        this.callbackHandler = new UsernamePasswordCallbackHandler("Guest", "password");
        this.options.put("debug", "true");
        this.loginModule.initialize(this.subject, this.callbackHandler, null, this.options);
        Assertions.assertTrue(this.loginModule.isAllowGuestLogin());
        Assertions.assertThrows(LoginException.class, () -> {
            this.loginModule.login();
        });
    }