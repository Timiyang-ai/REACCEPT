    public void test_fromPrivateKeyPemInputStream() throws Exception {
        ByteArrayInputStream is = new ByteArrayInputStream(RSA_PRIVATE_KEY.getBytes("UTF-8"));
        OpenSSLKey key = OpenSSLKey.fromPrivateKeyPemInputStream(is);
        OpenSSLRSAPrivateKey privateKey = (OpenSSLRSAPrivateKey)key.getPrivateKey();
        assertEquals(RSA_MODULUS, privateKey.getModulus());
        assertEquals(RSA_PRIVATE_EXPONENT, privateKey.getPrivateExponent());
    }