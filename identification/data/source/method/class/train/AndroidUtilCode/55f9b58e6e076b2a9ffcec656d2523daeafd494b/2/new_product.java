private static long getFileLength(final File file) {
        if (!isFile(file)) return -1;
        return file.length();
    }