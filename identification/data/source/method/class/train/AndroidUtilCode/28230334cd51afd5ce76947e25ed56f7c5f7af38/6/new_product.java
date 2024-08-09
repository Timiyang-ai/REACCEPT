public static byte[] encryptDES(final byte[] data, final byte[] key) {
        return desTemplate(data, key, DES_Algorithm, DES_Transformation, true);
    }