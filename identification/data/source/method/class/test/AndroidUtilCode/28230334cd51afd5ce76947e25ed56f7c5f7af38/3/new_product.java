public static byte[] encryptDES(byte[] data, byte[] key) {
        return desTemplate(data, key, DES_Algorithm, DES_Transformation, true);
    }