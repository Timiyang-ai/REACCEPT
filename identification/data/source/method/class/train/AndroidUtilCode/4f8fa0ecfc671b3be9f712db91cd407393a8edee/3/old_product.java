public static byte[] encryptSHA384(byte[] data) {
        return hashTemplate(data, "SHA384");
    }