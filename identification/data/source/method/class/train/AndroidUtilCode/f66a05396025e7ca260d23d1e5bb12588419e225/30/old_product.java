public static boolean isFile(final File file) {
        return isFileExists(file) && file.isFile();
    }