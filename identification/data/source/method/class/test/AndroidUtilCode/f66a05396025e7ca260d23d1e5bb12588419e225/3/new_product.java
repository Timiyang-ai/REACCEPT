public static boolean isDir(final File file) {
        return file != null && file.exists() && file.isDirectory();
    }