@Override
    public byte[] decrypt(byte[] data) throws CryptoException {
        try {
            byte[] plaintext;
            if (useExplicitIv) {
                decryptIv = new IvParameterSpec(Arrays.copyOf(data, decryptCipher.getBlockSize()));
            }
            if (tlsContext.getChooser().getConnectionEnd().getConnectionEndType() == ConnectionEndType.CLIENT) {
                decryptCipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(serverWriteKey, bulkCipherAlg.getJavaName()),
                        decryptIv);
            } else {
                decryptCipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(clientWriteKey, bulkCipherAlg.getJavaName()),
                        decryptIv);
            }
            if (useExplicitIv) {
                plaintext = decryptCipher.doFinal(Arrays.copyOfRange(data, decryptCipher.getBlockSize(), data.length));
            } else {
                plaintext = decryptCipher.doFinal(data);
                decryptIv = new IvParameterSpec(Arrays.copyOfRange(data, data.length - decryptCipher.getBlockSize(),
                        data.length));
            }
            return plaintext;
        } catch (BadPaddingException | IllegalBlockSizeException | InvalidAlgorithmParameterException
                | InvalidKeyException | UnsupportedOperationException ex) {
            throw new CryptoException(ex);
        }
    }