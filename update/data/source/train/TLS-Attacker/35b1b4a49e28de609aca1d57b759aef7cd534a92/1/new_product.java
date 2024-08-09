@Override
    public DecryptionResult decrypt(byte[] data) {
        try {
            byte[] plaintext;
            byte[] usedIv;
            ConnectionEndType localConEndType = context.getConnection().getLocalConnectionEndType();
            if (useExplicitIv) {
                decryptIv = new IvParameterSpec(Arrays.copyOf(data, decryptCipher.getBlockSize()));
                LOGGER.debug("decryptionIV: " + ArrayConverter.bytesToHexString(decryptIv.getIV()));

                decryptCipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(getKeySet().getReadKey(localConEndType),
                        bulkCipherAlg.getJavaName()), decryptIv);
                plaintext = decryptCipher.doFinal(Arrays.copyOfRange(data, decryptCipher.getBlockSize(), data.length));
                usedIv = decryptIv.getIV();
            } else {
                decryptIv = new IvParameterSpec(getDecryptionIV());
                LOGGER.debug("decryptionIV: " + ArrayConverter.bytesToHexString(decryptIv.getIV()));

                decryptCipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(getKeySet().getReadKey(localConEndType),
                        bulkCipherAlg.getJavaName()), decryptIv);
                plaintext = decryptCipher.doFinal(data);
                usedIv = decryptIv.getIV();
                // Set next IV
                setNextDecryptIv(data);
            }

            return new DecryptionResult(usedIv, plaintext, useExplicitIv);
        } catch (BadPaddingException | IllegalBlockSizeException | InvalidAlgorithmParameterException
                | InvalidKeyException | UnsupportedOperationException ex) {
            LOGGER.warn("Could not decrypt Data with the provided parameters. Returning undecrypted data.", ex);
            return new DecryptionResult(null, data, useExplicitIv);
        }
    }