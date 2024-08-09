public static String getFilePostfixName(String fileName){
        if (hasExtension(fileName)){
            return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        }
        return StringUtils.EMPTY;
    }