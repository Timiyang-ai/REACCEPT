    @Test(expected = NullPointerException.class)
    public void setLocalCertsAndPrivateKey_withNullSSLShouldThrow() throws Exception {
        NativeCrypto.setLocalCertsAndPrivateKey(
                NULL, null, getEncodedServerCertificates(), getServerPrivateKey().getNativeRef());
    }