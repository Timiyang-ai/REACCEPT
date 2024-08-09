public static byte[] encryptAES(final byte[] data, final byte[] key) {
        return desTemplate(data, key, AES_Algorithm, AES_Transformation, true);
    }