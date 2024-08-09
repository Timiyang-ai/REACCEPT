public static boolean unzipFile(File zipFile, File destDir)
            throws IOException {
        return unzipFileByKeyword(zipFile, destDir, null) != null;
    }