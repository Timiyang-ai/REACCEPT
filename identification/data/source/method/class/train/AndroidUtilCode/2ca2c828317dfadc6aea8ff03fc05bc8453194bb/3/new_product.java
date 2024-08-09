public static boolean move(final String srcPath,
                               final String destPath,
                               final OnReplaceListener listener) {
        return move(getFileByPath(srcPath), getFileByPath(destPath), listener);
    }