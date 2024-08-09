public static byte[] encryptSHA384(final byte[] data) {
        return hashTemplate(data, "SHA-384");
    }