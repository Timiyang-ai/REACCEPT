public static boolean isDir(File file) {
        return isFileExists(file) && file.isDirectory();
    }