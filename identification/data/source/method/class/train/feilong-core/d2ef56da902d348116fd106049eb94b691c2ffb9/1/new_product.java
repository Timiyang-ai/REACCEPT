public static String substring(final String text,final String startString,final String endString){
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