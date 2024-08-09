public static String addParameterSingleValueMap(String uriString,Map<String, String> singleValueMap,String charsetType){
        Validate.notNull(singleValueMap, "singleValueMap can't be null!");
        return addParameterArrayValueMap(uriString, MapUtil.toArrayValueMap(singleValueMap), charsetType);
    }