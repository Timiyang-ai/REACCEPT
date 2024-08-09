    @Test
    public void commit_noPrincipal() throws LoginException {
        Assertions.assertFalse(this.loginModule.commit());
    }