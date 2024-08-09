public static byte[] decrypt3DES(final byte[] data,
                                     final byte[] key,
                                     final String transformation,
                                     final byte[] iv) {
        return symmetricTemplate(data, key, "DESede", transformation, iv, false);
    }