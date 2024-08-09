public static byte[] encryptDES(final byte[] data,
                                    final byte[] key,
                                    final String transformation,
                                    final byte[] iv) {
        return desTemplate(data, key, DES_Algorithm, transformation, iv, true);
    }