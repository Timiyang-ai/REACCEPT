public static boolean writeFileFromIS(final File file,
                                          final InputStream is,
                                          final boolean append) {
        return writeFileFromIS(file, is, append, null);
    }