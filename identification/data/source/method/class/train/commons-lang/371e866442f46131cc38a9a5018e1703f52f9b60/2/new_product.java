public static String getRootCauseMessage(final Throwable th) {
        Throwable root = getRootCause(th);
        root = root == null ? th : root;
        return getMessage(root);
    }