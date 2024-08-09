public static byte[] encrypt3DES(final byte[] data, final byte[] key) {
        return desTemplate(data, key, TripleDES_Algorithm, TripleDES_Transformation, true);
    }