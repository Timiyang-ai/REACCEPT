public static List<String> getComments(String zipFilePath)
            throws IOException {
        return getComments(FileUtils.getFileByPath(zipFilePath));
    }