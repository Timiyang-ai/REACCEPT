public static byte[] deriveSecret(String macAlgorithm, byte[] prk, String labelIn, byte[] hashValue) {
        try {
            int outLen = Mac.getInstance(macAlgorithm).getMacLength();
            byte[] info = labelEncoder(hashValue, labelIn, outLen);
            byte[] result = expand(macAlgorithm, prk, info, outLen);
            return result;
        } catch (NoSuchAlgorithmException ex) {
            throw new CryptoException("Could not initialize HKDF", ex);
        }
    }