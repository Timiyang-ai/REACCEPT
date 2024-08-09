public static boolean hasPostfixName(String fileName){
        String fileNameString = getFileName(fileName);
        int lastIndexOf = fileNameString.lastIndexOf(".");
        return -1 != lastIndexOf;
    }