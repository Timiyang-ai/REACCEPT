public static byte[] encrypt3DES(byte[] data, byte[] key) {
        return DESTemplet(data, key, TripleDES_Algorithm, TripleDES_Transformation, true);
    }