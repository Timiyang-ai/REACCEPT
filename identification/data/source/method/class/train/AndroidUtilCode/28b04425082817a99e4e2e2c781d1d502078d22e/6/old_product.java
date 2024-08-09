public static String getFileName(final File file) {
        if (file == null) return null;
        return getFileName(file.getAbsolutePath());
    }