public static byte[] encryptMD5(byte[] data) {
        return hashTemplate(data, "MD5");
    }