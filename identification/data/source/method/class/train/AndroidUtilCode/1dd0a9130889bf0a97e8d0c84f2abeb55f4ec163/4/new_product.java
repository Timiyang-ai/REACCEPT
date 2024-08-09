public static boolean rename(final String filePath, final String newName) {
        return rename(getFileByPath(filePath), newName);
    }