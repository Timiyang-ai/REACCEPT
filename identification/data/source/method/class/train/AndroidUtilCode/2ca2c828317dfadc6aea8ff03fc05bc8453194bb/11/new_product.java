public static boolean copy(final String srcPath,
                               final String destPath) {
        return copy(getFileByPath(srcPath), getFileByPath(destPath), null);
    }