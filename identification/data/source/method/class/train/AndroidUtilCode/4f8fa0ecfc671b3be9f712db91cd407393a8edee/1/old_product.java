public static byte[] encryptAES(byte[] data, byte[] key) {
        return DESTemplet(data, key, AES_Algorithm, AES_Transformation, true);
    }