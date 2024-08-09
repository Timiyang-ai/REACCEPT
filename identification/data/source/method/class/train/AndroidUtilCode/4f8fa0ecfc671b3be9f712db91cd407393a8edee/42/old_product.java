public static byte[] encryptAES(byte[] data, byte[] key) {
        return desTemplate(data, key, AES_Algorithm, AES_Transformation, true);
    }