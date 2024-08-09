public static PublicKey extractPublicKey(byte[] signature) {
        Binder src = Boss.unpack(signature);
        PublicKey publicKey = null;
        byte[] exts = src.getBinaryOrThrow("exts");
        Binder b = Boss.unpack(exts);
        try {
            byte[] publicKeyBytes = b.getBinaryOrThrow("pub_key");
            publicKey = new PublicKey(publicKeyBytes);
        } catch (EncryptionError e) {
            publicKey = null;
        } catch (IllegalArgumentException e) {
            publicKey = null;
        }

        return publicKey;
    }