public static byte[] encryptSHA1(final byte[] data) {
        return hashTemplate(data, "SHA1");
    }