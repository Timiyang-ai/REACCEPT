public static List<String> readFile2List(String filePath) {
        return readFile2List(FileUtils.getFileByPath(filePath), null);
    }