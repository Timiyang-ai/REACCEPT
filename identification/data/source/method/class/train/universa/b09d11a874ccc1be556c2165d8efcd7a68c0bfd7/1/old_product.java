@Override
    public byte[] decrypt(byte[] ciphertext) throws EncryptionError {
        if (state == null) {
            throw new IllegalStateException();
        } else {
            try {
                return state.decryptor.processBlock(ciphertext, 0, ciphertext.length);
            } catch (InvalidCipherTextException e) {
                throw new EncryptionError(String.format("Cannot decode: %s", e.toString()));
            }
        }
    }