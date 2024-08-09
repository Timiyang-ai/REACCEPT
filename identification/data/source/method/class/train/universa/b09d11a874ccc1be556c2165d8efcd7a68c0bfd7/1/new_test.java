    @Test
    public void decrypt() throws Exception {
        // Test on RSA vectors.
        AbstractPrivateKey rsaPrivateKey = oaepSpec.getPrivateKey();
        AbstractPublicKey rsaPublicKey = rsaPrivateKey.getPublicKey();
        ((RSAOAEPPrivateKey) rsaPrivateKey).resetDecryptor();
        assertArrayEquals(rsaPrivateKey.decrypt(oaepSpec.C), oaepSpec.M);
        ((RSAOAEPPublicKey) rsaPublicKey).resetEncryptor();
        assertArrayEquals(rsaPublicKey.encrypt(oaepSpec.M), oaepSpec.C);

        // Test on some random data.
        AbstractPrivateKey randomPrivateKey = randomPrivateKey4096;
        AbstractPublicKey randomPublicKey = randomPrivateKey.getPublicKey();

        SecureRandom rng = new SecureRandom();

        int
                maxMessageSize = ((RSAOAEPPrivateKey) randomPrivateKey).getMaxBlockSize(),
                minMessageSize = maxMessageSize,
                messageSize = (maxMessageSize >= minMessageSize) ?
                        rng.nextInt(maxMessageSize - minMessageSize + 1) + minMessageSize :
                        0;

        // For our key pair we do multiple encryption-decryption cycles on different data.
        for (int i = 0; i < NUMBER_OF_RANDOM_ENCRYPTION_DECRYPTION_CYCLES; i++) {
            ((RSAOAEPPrivateKey) randomPrivateKey).resetDecryptor();
            ((RSAOAEPPublicKey) randomPublicKey).resetEncryptor();
            // Create random message
            byte[] message = new byte[messageSize];
            rng.nextBytes(message);
            byte[]
                    encrypted = randomPublicKey.encrypt(message),
                    decrypted = randomPrivateKey.decrypt(encrypted);
            assertArrayEquals(decrypted, message);
        }
    }