public static String getDirName(final File file) {
        if (file == null) return "";
        return getDirName(file.getAbsolutePath());
    }