public static String getImageType(String filePath) {
        return getImageType(FileUtils.getFileByPath(filePath));
    }