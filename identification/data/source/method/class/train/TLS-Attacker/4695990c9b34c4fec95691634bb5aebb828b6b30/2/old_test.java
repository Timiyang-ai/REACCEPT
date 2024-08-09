@Test
    public void testEncrypt() throws NoSuchAlgorithmException {
        context.setActiveServerKeySetType(Tls13KeySetType.HANDSHAKE_TRAFFIC_SECRETS);
        context.setConnection(new InboundConnection());
        this.cipher = new RecordAEADCipher(context, KeySetGenerator.generateKeySet(context));
        byte[] plaintext = ArrayConverter.hexStringToByteArray("08000002000016");
        byte[] ciphertext = cipher.encrypt(new EncryptionRequest(plaintext)).getCompleteEncryptedCipherText();
        byte[] ciphertext_correct = ArrayConverter
                .hexStringToByteArray("1BB3293A919E0D66F145AE830488E8D89BE5EC16688229");
        assertArrayEquals(ciphertext, ciphertext_correct);
    }