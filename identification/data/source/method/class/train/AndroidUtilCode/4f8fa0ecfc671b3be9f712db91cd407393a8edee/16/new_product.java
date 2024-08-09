public static byte[] encryptSHA224(byte[] data) {
        return hashTemplate(data, "SHA224");
    }