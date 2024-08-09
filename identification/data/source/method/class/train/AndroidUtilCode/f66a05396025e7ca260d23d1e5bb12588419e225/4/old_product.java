public static boolean writeFileFromString(String filePath, String content) {
        return writeFileFromString(FileUtils.getFileByPath(filePath), content, false);
    }