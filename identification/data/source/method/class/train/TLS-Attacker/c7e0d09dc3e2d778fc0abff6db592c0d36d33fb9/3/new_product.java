@Override
    public byte[] decrypt(byte[] data) throws CryptoException {
        try {
            byte[] plaintext;
            if (useExplicitIv) {
                decryptIv = new IvParameterSpec(Arrays.copyOf(data, decryptCipher.getBlockSize()));
                if (context.getConnectionEnd().getConnectionEndType() == ConnectionEndType.CLIENT) {
                    decryptCipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(getKeySet().getServerWriteKey(),
                            bulkCipherAlg.getJavaName()), decryptIv);
                } else {
                    decryptCipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(getKeySet().getClientWriteKey(),
                            bulkCipherAlg.getJavaName()), decryptIv);
                }
                plaintext = decryptCipher.doFinal(Arrays.copyOfRange(data, decryptCipher.getBlockSize(), data.length));
            } else {
                plaintext = decryptCipher.update(data);
            }
            return plaintext;
        } catch (BadPaddingException | IllegalBlockSizeException | InvalidAlgorithmParameterException
                | InvalidKeyException | UnsupportedOperationException ex) {
            throw new CryptoException(ex);
        }
    }