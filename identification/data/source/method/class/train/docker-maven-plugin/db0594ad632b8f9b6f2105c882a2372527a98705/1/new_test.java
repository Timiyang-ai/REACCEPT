    @Test
    public void createKeyStore() throws Exception {
        KeyStore keyStore = KeyStoreUtil.createDockerKeyStore(getFile("certpath"));
        KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry("docker",
                new KeyStore.PasswordProtection("docker".toCharArray()));
        assertNotNull(pkEntry);
        assertNotNull(pkEntry.getCertificate());
        assertNotNull(keyStore.getCertificate("cn=ca-test,o=internet widgits pty ltd,st=some-state,c=cr"));
        assertNotNull(keyStore.getCertificate("cn=ca-test-2,o=internet widgits pty ltd,st=some-state,c=cr"));
    }