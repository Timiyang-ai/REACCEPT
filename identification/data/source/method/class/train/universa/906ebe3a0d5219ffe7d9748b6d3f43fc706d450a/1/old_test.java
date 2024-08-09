    @Test
    public void sign() throws Exception {
        // Test on RSA vectors.
        AbstractPrivateKey rsaPrivateKey = pssSpec.getPrivateKey();
        assertArrayEquals(rsaPrivateKey.sign(pssSpec.M, HashType.SHA1, RSASSAPSSTestVectors.salt), pssSpec.S);
    }