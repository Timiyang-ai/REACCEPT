public static File getFileByPath(String filePath) {
        return isSpace(filePath) ? null : new File(filePath);
    }