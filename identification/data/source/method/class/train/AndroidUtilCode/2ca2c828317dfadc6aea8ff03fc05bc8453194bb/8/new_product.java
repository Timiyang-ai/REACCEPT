public static long getLength(final File file) {
        if (file == null) return 0;
        if (file.isDirectory()) {
            return getDirLength(file);
        }
        return getFileLength(file);
    }