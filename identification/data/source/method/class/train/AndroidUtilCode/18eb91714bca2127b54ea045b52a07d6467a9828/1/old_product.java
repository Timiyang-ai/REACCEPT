public static boolean unzipFile(final String zipFilePath, final String destDirPath)
            throws IOException {
        return unzipFile(FileUtils.getFileByPath(zipFilePath), FileUtils.getFileByPath(destDirPath));
    }