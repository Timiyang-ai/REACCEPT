public static boolean isDir(final File file) {
        return isFileExists(file) && file.isDirectory();
    }