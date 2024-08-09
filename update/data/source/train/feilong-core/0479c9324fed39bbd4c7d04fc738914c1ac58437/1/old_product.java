public static String removeParameter(String uriString,String paramName,String charsetType){
        if (Validator.isNullOrEmpty(uriString)){
            return StringUtils.EMPTY;
        }
        if (null == paramName){
            return uriString;
        }
        List<String> list = new ArrayList<String>();
        list.add(paramName);
        return removeParameterList(uriString, list, charsetType);
    }