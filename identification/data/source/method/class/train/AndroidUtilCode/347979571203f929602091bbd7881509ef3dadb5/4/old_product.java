public static byte[] encryptMD5File(String filePath) {
        File file = StringUtils.isSpace(filePath) ? null : new File(filePath);
        return encryptMD5File(file);
    }