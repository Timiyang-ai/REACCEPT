@Override
    public byte[] encrypt(byte[] data) throws CryptoException {
        try {
            byte[] ciphertext;
            if (useExplicitIv) {
                // Note: here we always use the same IV that was generated from
                // the master secret
                ciphertext = ArrayConverter.concatenate(encryptIv.getIV(), encryptCipher.doFinal(data));
            } else {
                ciphertext = encryptCipher.update(data);
            }
            return ciphertext;
        } catch (BadPaddingException | IllegalBlockSizeException ex) {
            throw new CryptoException(ex);
        }
    }