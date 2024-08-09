public static byte[] encryptSHA512(byte[] data) {
        return hashTemplate(data, "SHA512");
    }