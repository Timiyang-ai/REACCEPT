public static boolean unzipFile(String zipFilePath, String destDirPath)
            throws IOException {
        return unzipFile(FileUtils.getFileByPath(zipFilePath), FileUtils.getFileByPath(destDirPath));
    }