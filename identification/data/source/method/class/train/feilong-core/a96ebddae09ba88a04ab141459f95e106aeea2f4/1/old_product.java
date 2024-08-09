public static boolean isContainIgnoreCase(Object text,String beIncludedString){
        if (null == text){
            LOGGER.warn("the param [text] is null,default return false");
            return false;
        }
        if (null == beIncludedString){
            LOGGER.warn("the param [beIncludedString] is null,default return false");
            return false;
        }
        return isContain(text.toString().toLowerCase(), beIncludedString.toLowerCase());
    }