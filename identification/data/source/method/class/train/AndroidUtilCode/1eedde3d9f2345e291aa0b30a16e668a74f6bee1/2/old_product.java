public static boolean zipFiles(Collection<File> resFiles, String zipFilePath, String comment)
            throws IOException {
        return zipFiles(resFiles, FileUtils.getFileByPath(zipFilePath), comment);
    }