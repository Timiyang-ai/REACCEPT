@Override
    public byte[] decrypt(byte[] data) throws CryptoException {
        try {
            byte[] plaintext;
            ConnectionEndType localConEndType = context.getConnection().getLocalConnectionEndType();
            if (useExplicitIv) {
                decryptIv = new IvParameterSpec(Arrays.copyOf(data, decryptCipher.getBlockSize()));
                LOGGER.debug("decryptionIV: " + ArrayConverter.bytesToHexString(decryptIv.getIV()));

                decryptCipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(getKeySet().getReadKey(localConEndType),
                        bulkCipherAlg.getJavaName()), decryptIv);
                plaintext = decryptCipher.doFinal(Arrays.copyOfRange(data, decryptCipher.getBlockSize(), data.length));
            } else {
                decryptIv = new IvParameterSpec(getDecryptionIV());
                LOGGER.debug("decryptionIV: " + ArrayConverter.bytesToHexString(decryptIv.getIV()));

                decryptCipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(getKeySet().getReadKey(localConEndType),
                        bulkCipherAlg.getJavaName()), decryptIv);
                plaintext = decryptCipher.doFinal(data);

                // Set next IV
                setNextDecryptIv(data);
            }

            return plaintext;
        } catch (BadPaddingException | IllegalBlockSizeException | InvalidAlgorithmParameterException
                | InvalidKeyException | UnsupportedOperationException ex) {
            throw new CryptoException(ex);
        }
    }