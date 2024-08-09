public static String joinValues(Map<String, String> singleValueMap,String...includeKeys){
        Validate.notNull(singleValueMap, "singleValueMap can't be null!");

        if (Validator.isNullOrEmpty(includeKeys)){
            return StringUtils.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        //有顺序的参数
        for (String key : includeKeys){
            String value = singleValueMap.get(key);

            //value转换,注意:如果 value 是null ,StringBuilder将拼接 "null" 字符串, 详见  java.lang.AbstractStringBuilder#append(String)
            sb.append(StringUtils.defaultString(value));
        }
        return sb.toString();
    }