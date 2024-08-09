public static List<String> readFile2List(String filePath, int st, int end, String charsetName) {
        return readFile2List(FileUtils.getFileByPath(filePath), st, end, charsetName);
    }