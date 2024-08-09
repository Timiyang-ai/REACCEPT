public static File getFileByPath(final String filePath) {
        return isSpace(filePath) ? null : new File(filePath);
    }