public static byte[] encryptSHA256(byte[] data) {
        return hashTemplate(data, "SHA256");
    }