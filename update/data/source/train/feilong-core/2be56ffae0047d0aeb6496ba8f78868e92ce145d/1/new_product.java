public static String getExtension(String fileName){
        return StringUtils.defaultString(org.apache.commons.io.FilenameUtils.getExtension(fileName));
    }