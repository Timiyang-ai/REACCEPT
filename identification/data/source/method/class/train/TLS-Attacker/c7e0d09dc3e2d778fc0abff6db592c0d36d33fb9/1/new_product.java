@Override
    public EncryptionResult encrypt(EncryptionRequest request) throws CryptoException {
        try {
            byte[] ciphertext;
            if (useExplicitIv) {
                encryptIv = new IvParameterSpec(request.getInitialisationVector());
                encryptCipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(getKeySet().getKey(context.getTalkingConnectionEndType()),bulkCipherAlg.getJavaName()),encryptIv);
                ciphertext = encryptCipher.doFinal(request.getPlainText());
                return new EncryptionResult(encryptIv.getIV(), ciphertext, useExplicitIv);

            } else {
                byte[] iv = encryptCipher.getIV();
                ciphertext = encryptCipher.update(request.getPlainText());
                return new EncryptionResult(ciphertext, iv, false);
            }
        } catch (InvalidKeyException | InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException ex) {
            throw new CryptoException(ex);
        }
    }