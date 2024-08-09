public static byte[] deriveSecret(HKDFAlgorithm hkdfAlgortihm, String hashAlgorithm, byte[] prk, String labelIn,
            byte[] toHash) throws CryptoException {
        try {
            MessageDigest hashFunction = MessageDigest.getInstance(hashAlgorithm);
            hashFunction.update(toHash);
            byte[] hashValue = hashFunction.digest();
            int outLen = Mac.getInstance(hkdfAlgortihm.getMacAlgorithm().getJavaName()).getMacLength();
            return expandLabel(hkdfAlgortihm, prk, labelIn, hashValue, outLen);
        } catch (NoSuchAlgorithmException ex) {
            throw new CryptoException("Could not initialize HKDF", ex);
        }
    }