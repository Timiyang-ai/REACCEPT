static public byte[] sign(PrivateKey key, byte[] data) {
        return sign(key, data, false);
    }