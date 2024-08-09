public static void zipFiles(Collection<File> resFiles, String zipFilePath, String comment)
            throws IOException {
        zipFiles(resFiles, FileUtils.getFileByPath(zipFilePath), comment);
    }