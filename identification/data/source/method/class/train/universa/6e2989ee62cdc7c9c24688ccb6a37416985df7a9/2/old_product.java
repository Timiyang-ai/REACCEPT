static public byte[] sign(PrivateKey key, byte[] data, boolean savePublicKey) {
        try {
            Binder targetSignatureBinder = Binder.fromKeysValues(
                    "key", keyId(key),
                    "sha512", new Sha512().digest(data),
                    "sha3_384", new Sha3_384().digest(data),
                    "created_at", ZonedDateTime.now()
            );
            if (savePublicKey)
                targetSignatureBinder.put("pub_key", key.getPublicKey().pack());
            byte[] targetSignature = Boss.pack(targetSignatureBinder);
            Binder result = Binder.fromKeysValues(
                    "exts", targetSignature,
                    "sign", key.sign(targetSignature, HashType.SHA512),
                    "sign2", key.sign(targetSignature, HashType.SHA3_384)
            );
            return Boss.pack(result);
        } catch (EncryptionError e) {
            throw new RuntimeException("signature failed", e);
        }
    }