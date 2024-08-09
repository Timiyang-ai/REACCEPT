    public void test_fromPublicKeyPemInputStream() throws Exception {
        ByteArrayInputStream is = new ByteArrayInputStream(RSA_PUBLIC_KEY.getBytes("UTF-8"));
        OpenSSLKey key = OpenSSLKey.fromPublicKeyPemInputStream(is);
        OpenSSLRSAPublicKey publicKey = (OpenSSLRSAPublicKey)key.getPublicKey();
        assertEquals(RSA_MODULUS, publicKey.getModulus());
        assertEquals(RSA_PUBLIC_EXPONENT, publicKey.getPublicExponent());
    }