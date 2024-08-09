public static byte[] encryptMD5(final byte[] data) {
        return hashTemplate(data, "MD5");
    }