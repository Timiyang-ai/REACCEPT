public static String getDirName(final File file) {
        if (file == null) return null;
        return getDirName(file.getAbsolutePath());
    }