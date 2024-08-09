static public byte[] sign(PrivateKey key, byte[] data) {
        try {
            byte[] targetSignature = Boss.pack(
                    Binder.fromKeysValues(
                            "key", keyId(key),
                            "sha512", new Sha512().digest(data),
                            "created_at", ZonedDateTime.now()
                    )
            );
            Binder result = Binder.fromKeysValues(
                    "exts", targetSignature,
                    "sign", key.sign(targetSignature, HashType.SHA512)
            );
            return Boss.pack(result);
        } catch (EncryptionError e) {
            throw new RuntimeException("signature failed", e);
        }
    }