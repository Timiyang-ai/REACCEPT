public static byte[] encryptSHA256(final byte[] data) {
        return hashTemplate(data, "SHA-256");
    }