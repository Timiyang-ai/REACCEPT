public static boolean isFile(final File file) {
        return file != null && file.exists() && file.isFile();
    }