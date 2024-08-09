public static byte[] encryptMD5File(String filePath) {
        return encryptMD5File(FileUtils.getFileByPath(filePath));
    }