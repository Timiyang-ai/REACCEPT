    @Test
    public void etaEncrypt() throws Exception {
        SymmetricKey k = new SymmetricKey();
        byte[] plainText = "Hello, world!".getBytes();
        byte[] cipherText = k.etaEncrypt(plainText);
//        Bytes.dump(cipherText);
        assertEquals(16 + 32 + plainText.length, cipherText.length);
        byte[] decryptedText = k.etaDecrypt(cipherText);
        assertArrayEquals(plainText, decryptedText);

        exception.expect(SymmetricKey.AuthenticationFailed.class);
        cipherText[19] += 1;
        k.etaDecrypt(cipherText);
    }