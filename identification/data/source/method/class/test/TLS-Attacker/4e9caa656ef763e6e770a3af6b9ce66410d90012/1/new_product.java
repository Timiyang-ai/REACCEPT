public static byte[] deriveSecret(String macAlgorithm, String hashAlgorithm, byte[] prk, String labelIn,
            byte[] toHash) {
        try {
            MessageDigest hashFunction = MessageDigest.getInstance(hashAlgorithm);
            hashFunction.update(toHash);
            byte[] hashValue = hashFunction.digest();
            int outLen = Mac.getInstance(macAlgorithm).getMacLength();
            return expandLabel(macAlgorithm, prk, labelIn, hashValue, outLen);
        } catch (NoSuchAlgorithmException ex) {
            throw new CryptoException("Could not initialize HKDF", ex);
        }
    }