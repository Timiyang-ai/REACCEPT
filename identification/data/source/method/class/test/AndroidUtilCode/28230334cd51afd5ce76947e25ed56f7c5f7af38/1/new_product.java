public static byte[] decryptDES(final byte[] data,
                                    final byte[] key,
                                    final String transformation,
                                    final byte[] iv) {
        return symmetricTemplate(data, key, "DES", transformation, iv, false);
    }