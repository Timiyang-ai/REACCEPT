public static String getImageType(final String filePath) {
        return getImageType(FileUtils.getFileByPath(filePath));
    }