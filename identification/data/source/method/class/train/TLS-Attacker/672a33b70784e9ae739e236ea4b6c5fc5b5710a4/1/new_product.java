@Override
    public DecryptionResult decrypt(DecryptionRequest decryptionRequest) {
        try {
            byte[] plaintext;
            byte[] usedIv;
            ConnectionEndType localConEndType = context.getConnection().getLocalConnectionEndType();
            if (useExplicitIv) {
                decryptIv = new IvParameterSpec(Arrays.copyOf(decryptionRequest.getCipherText(),
                        decryptCipher.getBlockSize()));
                LOGGER.debug("decryptionIV: " + ArrayConverter.bytesToHexString(decryptIv.getIV()));

                decryptCipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(getKeySet().getReadKey(localConEndType),
                        bulkCipherAlg.getJavaName()), decryptIv);
                plaintext = decryptCipher.doFinal(Arrays.copyOfRange(decryptionRequest.getCipherText(),
                        decryptCipher.getBlockSize(), decryptionRequest.getCipherText().length));
                usedIv = decryptIv.getIV();
            } else {
                decryptIv = new IvParameterSpec(getDecryptionIV());
                LOGGER.debug("decryptionIV: " + ArrayConverter.bytesToHexString(decryptIv.getIV()));

                decryptCipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(getKeySet().getReadKey(localConEndType),
                        bulkCipherAlg.getJavaName()), decryptIv);
                plaintext = decryptCipher.doFinal(decryptionRequest.getCipherText());
                usedIv = decryptIv.getIV();
                // Set next IV
                setNextDecryptIv(decryptionRequest.getCipherText());
            }

            return new DecryptionResult(usedIv, plaintext, useExplicitIv);
        } catch (BadPaddingException | IllegalBlockSizeException | InvalidAlgorithmParameterException
                | InvalidKeyException | UnsupportedOperationException ex) {
            LOGGER.warn("Could not decrypt Data with the provided parameters. Returning undecrypted data.", ex);
            return new DecryptionResult(null, decryptionRequest.getCipherText(), useExplicitIv);
        }
    }