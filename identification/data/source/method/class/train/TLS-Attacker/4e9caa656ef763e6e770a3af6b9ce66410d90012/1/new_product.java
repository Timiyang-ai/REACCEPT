public static byte[] expandLabel(String macAlgorithm, byte[] prk, String labelIn, byte[] hashValue, int outLen) {
        byte[] info = labelEncoder(hashValue, labelIn, outLen);
        return expand(macAlgorithm, prk, info, outLen);
    }