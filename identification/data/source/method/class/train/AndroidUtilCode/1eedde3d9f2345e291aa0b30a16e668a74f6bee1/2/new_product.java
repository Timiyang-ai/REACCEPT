public static boolean zipFiles(final Collection<File> resFiles, final String zipFilePath, final String comment)
            throws IOException {
        return zipFiles(resFiles, FileUtils.getFileByPath(zipFilePath), comment);
    }