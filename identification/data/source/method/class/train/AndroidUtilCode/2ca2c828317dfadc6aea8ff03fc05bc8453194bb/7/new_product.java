public static boolean move(final String srcPath,
                               final String destPath) {
        return move(getFileByPath(srcPath), getFileByPath(destPath), null);
    }