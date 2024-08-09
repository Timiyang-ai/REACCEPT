public static byte[] encryptDES(byte[] data, byte[] key) {
        return DESTemplet(data, key, DES_Algorithm, DES_Transformation, true);
    }