@Override
    public DecryptionResult decrypt(DecryptionRequest decryptionRequest) {
        try {
            byte[] plaintext;
            byte[] usedIv;
            if (decryptionRequest.getCipherText().length % decryptCipher.getBlocksize() != 0) {
                LOGGER.warn("Ciphertext is not a multiple of the Blocksize. Not Decrypting");
                return new DecryptionResult(new byte[0], decryptionRequest.getCipherText(), useExplicitIv);
            }
            ConnectionEndType localConEndType = context.getConnection().getLocalConnectionEndType();
            if (useExplicitIv) {
                byte[] decryptIv = Arrays.copyOf(decryptionRequest.getCipherText(), decryptCipher.getBlocksize());
                LOGGER.debug("decryptionIV: " + ArrayConverter.bytesToHexString(decryptIv));
                plaintext = decryptCipher.decrypt(getKeySet().getReadKey(localConEndType), decryptIv, Arrays
                        .copyOfRange(decryptionRequest.getCipherText(), decryptCipher.getBlocksize(),
                                decryptionRequest.getCipherText().length));
                usedIv = decryptIv;
            } else {
                byte[] decryptIv = getDecryptionIV();
                LOGGER.debug("decryptionIV: " + ArrayConverter.bytesToHexString(decryptIv));
                plaintext = decryptCipher.decrypt(getKeySet().getReadKey(localConEndType), decryptIv,
                        decryptionRequest.getCipherText());
                usedIv = decryptIv;
                // Set next IV
            }

            return new DecryptionResult(usedIv, plaintext, useExplicitIv);
        } catch (CryptoException | UnsupportedOperationException ex) {
            LOGGER.warn("Could not decrypt Data with the provided parameters. Returning undecrypted data.", ex);
            return new DecryptionResult(null, decryptionRequest.getCipherText(), useExplicitIv);
        }
    }