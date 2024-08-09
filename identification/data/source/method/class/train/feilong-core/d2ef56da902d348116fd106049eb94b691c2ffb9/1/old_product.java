public static String substring(String text,String startString,String endString){
        if (Validator.isNullOrEmpty(text)){
            return StringUtils.EMPTY;
        }
        if (Validator.isNullOrEmpty(startString)){
            return text.substring(0, text.indexOf(endString));
        }
        int beginIndex = text.indexOf(startString);
        int endIndex = text.indexOf(endString);
        return text.substring(beginIndex, endIndex);
    }