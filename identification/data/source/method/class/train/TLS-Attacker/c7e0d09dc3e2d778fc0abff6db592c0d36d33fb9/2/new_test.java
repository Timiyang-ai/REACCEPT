@Test
    public void testDecrypt() throws NoSuchAlgorithmException {
        context.setConnectionEnd(new ClientConnectionEnd());
        this.cipher = new RecordAEADCipher(context, KeySetGenerator.generateKeySet(context));
        byte[] ciphertext = ArrayConverter.hexStringToByteArray("1BB3293A919E0D66F145AE830488E8D89BE5EC16688229");
        byte[] plaintext = cipher.decrypt(ciphertext);
        byte[] plaintext_correct = ArrayConverter.hexStringToByteArray("08000002000016");
        assertArrayEquals(plaintext, plaintext_correct);
    }