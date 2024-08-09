    @Test
    public void checkSignature() throws Exception {
        // Test sample RSA vectors.
        AbstractPublicKey rsaPublicKey = pssSpec.getPublicKey();
        AbstractPrivateKey rsaPrivateKey = pssSpec.getPrivateKey();
        assertArrayEquals(
                rsaPrivateKey.sign(pssSpec.M, HashType.SHA1, RSASSAPSSTestVectors.salt),
                pssSpec.S);
        assertTrue(rsaPublicKey.checkSignature(
                pssSpec.M,
                rsaPrivateKey.sign(pssSpec.M, HashType.SHA1, RSASSAPSSTestVectors.salt),
                HashType.SHA1,
                RSASSAPSSTestVectors.salt.length));
    }