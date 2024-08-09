public static boolean rename(String filePath, String newName) {
        return rename(getFileByPath(filePath), newName);
    }