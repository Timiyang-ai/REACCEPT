public static String toQueryStringUseSingleValueMap(Map<String, String> singleValueMap){
        return Validator.isNullOrEmpty(singleValueMap) ? StringUtils.EMPTY
                        : toQueryStringUseArrayValueMap(toArrayValueMap(singleValueMap));
    }