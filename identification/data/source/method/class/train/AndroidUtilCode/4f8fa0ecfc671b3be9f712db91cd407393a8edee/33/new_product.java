public static byte[] encryptSHA224(final byte[] data) {
        return hashTemplate(data, "SHA224");
    }