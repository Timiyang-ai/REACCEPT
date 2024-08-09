public static List<String> getFilesPath(String zipFilePath)
            throws IOException {
        return getFilesPath(FileUtils.getFileByPath(zipFilePath));
    }