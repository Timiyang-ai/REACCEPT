public static boolean isFile(File file) {
        return isFileExists(file) && file.isFile();
    }