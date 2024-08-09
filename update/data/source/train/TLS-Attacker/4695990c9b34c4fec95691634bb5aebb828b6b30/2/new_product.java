public static byte[] expandLabel(HKDFAlgorithm hkdfAlgortihm, byte[] prk, String labelIn, byte[] hashValue,
            int outLen) throws CryptoException {
        byte[] info = labelEncoder(hashValue, labelIn, outLen);
        return expand(hkdfAlgortihm, prk, info, outLen);
    }