    @Test
    public void comparePasswords_sha256() throws Exception {
        setPasswordTypes("sha256");
        assertTrue("password should be sha256", jdbcAuthProvider.comparePasswords(PASSWORD, SHA256_PASSWORD));
    }