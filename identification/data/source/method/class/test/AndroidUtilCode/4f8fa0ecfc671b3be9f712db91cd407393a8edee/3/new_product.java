public static byte[] encryptSHA1(byte[] data) {
        return hashTemplate(data, "SHA1");
    }