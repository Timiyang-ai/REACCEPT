public static boolean writeFileFromIS(String filePath, final InputStream is) {
        return writeFileFromIS(FileUtils.getFileByPath(filePath), is, false);
    }