public static List<String> getComments(final String zipFilePath)
            throws IOException {
        return getComments(getFileByPath(zipFilePath));
    }