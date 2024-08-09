public static List<String> readFile2List(String filePath, String charsetName) {
        return readFile2List(FileUtils.getFileByPath(filePath), charsetName);
    }