public static String readFile2String(String filePath, String charsetName) {
        return readFile2String(FileUtils.getFileByPath(filePath), charsetName);
    }