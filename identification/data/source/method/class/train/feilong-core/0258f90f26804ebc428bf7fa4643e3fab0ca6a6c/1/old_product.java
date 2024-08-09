public static String addParameterSingleValueMap(String uriString,Map<String, String> singleValueMap,String charsetType){
        return addParameterArrayValueMap(uriString, MapUtil.toArrayValueMap(singleValueMap), charsetType);
    }