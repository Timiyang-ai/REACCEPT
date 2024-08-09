public static String joinSingleValueMap(Map<String, String> singleValueMap){
        if (Validator.isNullOrEmpty(singleValueMap)){
            return StringUtils.EMPTY;
        }
        Map<String, String[]> arrayValueMap = toArrayValueMap(singleValueMap);
        return joinArrayValueMap(arrayValueMap);
    }