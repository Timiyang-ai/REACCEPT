public static byte[] encryptSHA512(final byte[] data) {
        return hashTemplate(data, "SHA512");
    }