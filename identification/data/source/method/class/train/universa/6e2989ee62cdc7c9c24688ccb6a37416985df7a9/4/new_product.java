static public byte[] sign(PrivateKey key, byte[] data, boolean savePublicKey) {
        try {
            byte[] targetSignature = ExtendedSignature.createTargetSignature(key.getPublicKey(),data,savePublicKey);

            return ExtendedSignature.ofSha512AndSha3_384(targetSignature,
                    key.sign(targetSignature, HashType.SHA512),
                    key.sign(targetSignature, HashType.SHA3_384));

        } catch (EncryptionError e) {
            throw new RuntimeException("signature failed", e);
        }
    }