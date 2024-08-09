public static List<String> getFilesPath(final String zipFilePath)
            throws IOException {
        return getFilesPath(FileUtils.getFileByPath(zipFilePath));
    }