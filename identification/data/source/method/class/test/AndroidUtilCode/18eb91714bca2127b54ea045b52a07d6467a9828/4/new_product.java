public static boolean unzipFile(final File zipFile, final File destDir)
            throws IOException {
        return unzipFileByKeyword(zipFile, destDir, null) != null;
    }