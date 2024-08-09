public static boolean writeFileFromIS(String filePath, final InputStream is, boolean append) {
        return writeFileFromIS(FileUtils.getFileByPath(filePath), is, append);
    }