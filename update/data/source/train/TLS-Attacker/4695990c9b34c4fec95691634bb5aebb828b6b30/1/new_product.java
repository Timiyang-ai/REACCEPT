@Override
    public EncryptionResult encrypt(EncryptionRequest request) {
        try {
            byte[] ciphertext;
            encryptIv = new IvParameterSpec(request.getInitialisationVector());
            encryptCipher.init(
                    Cipher.ENCRYPT_MODE,
                    new SecretKeySpec(getKeySet().getWriteKey(context.getTalkingConnectionEndType()), bulkCipherAlg
                            .getJavaName()), encryptIv);
            ciphertext = encryptCipher.update(request.getPlainText());
            if (!useExplicitIv) {
                setNextEncryptIv(ciphertext);
            }
            LOGGER.debug("encryptIv: " + ArrayConverter.bytesToHexString(encryptIv.getIV()));
            return new EncryptionResult(encryptIv.getIV(), ciphertext, useExplicitIv);

        } catch (InvalidKeyException | InvalidAlgorithmParameterException ex) {
            LOGGER.warn("Could not decrypt Data with the provided parameters. Returning unencrypted data.", ex);
            return new EncryptionResult(request.getPlainText());
        }
    }