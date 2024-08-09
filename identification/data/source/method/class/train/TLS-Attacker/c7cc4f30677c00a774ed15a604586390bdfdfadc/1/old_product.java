@Override
    public byte[] encrypt(byte[] data) throws CryptoException {
        try {
            byte[] ciphertext;
            if (useExplicitIv) {
                ciphertext = ArrayConverter.concatenate(encryptIv.getIV(), encryptCipher.doFinal(data));
            } else {
                encryptCipher.init(Cipher.ENCRYPT_MODE, encryptKey, encryptIv);
                ciphertext = encryptCipher.doFinal(data);
                encryptIv = new IvParameterSpec(Arrays.copyOfRange(ciphertext,
                        ciphertext.length - decryptCipher.getBlockSize(), ciphertext.length));
            }
            return ciphertext;
        } catch (BadPaddingException | IllegalBlockSizeException | InvalidAlgorithmParameterException
                | InvalidKeyException ex) {
            throw new CryptoException(ex);
        }
    }