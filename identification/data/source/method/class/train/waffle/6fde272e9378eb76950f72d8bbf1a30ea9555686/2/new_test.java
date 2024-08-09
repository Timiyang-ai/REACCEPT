    @Test
    public void logout_abortNoUser() throws LoginException {
        this.loginModule.initialize(this.subject, this.callbackHandler, null, this.options);
        Assertions.assertTrue(this.loginModule.abort());
    }