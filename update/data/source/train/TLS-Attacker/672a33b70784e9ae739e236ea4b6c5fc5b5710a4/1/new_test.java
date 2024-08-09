@Test
    public void testDecrypt() throws NoSuchAlgorithmException, CryptoException {
        context.setActiveClientKeySetType(Tls13KeySetType.HANDSHAKE_TRAFFIC_SECRETS);
        context.setConnection(new OutboundConnection());
        this.cipher = new RecordAEADCipher(context, KeySetGenerator.generateKeySet(context));
        byte[] ciphertext = ArrayConverter.hexStringToByteArray("1BB3293A919E0D66F145AE830488E8D89BE5EC16688229");
        byte[] plaintext = cipher.decrypt(new DecryptionRequest(null, ciphertext)).getDecryptedCipherText();
        byte[] plaintext_correct = ArrayConverter.hexStringToByteArray("08000002000016");
        assertArrayEquals(plaintext, plaintext_correct);
    }