public static boolean writeFileFromString(String filePath, String content, boolean append) {
        return writeFileFromString(FileUtils.getFileByPath(filePath), content, append);
    }