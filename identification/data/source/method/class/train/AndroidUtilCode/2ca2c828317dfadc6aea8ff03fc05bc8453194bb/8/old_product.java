public static long getDirLength(final String dirPath) {
        return getDirLength(getFileByPath(dirPath));
    }