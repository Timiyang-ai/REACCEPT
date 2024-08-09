public static byte[] encrypt3DES(final byte[] data,
                                     final byte[] key,
                                     final String transformation,
                                     final byte[] iv) {
        return desTemplate(data, key, TripleDES_Algorithm, transformation, iv, true);
    }