public static Map<String, String[]> toSafeArrayValueMap(String queryString,String charsetType){
        if (Validator.isNullOrEmpty(queryString)){
            return Collections.emptyMap();
        }
        boolean needEncode = Validator.isNotNullOrEmpty(charsetType);

        String[] nameAndValueArray = StringUtil.split(queryString, URIComponents.AMPERSAND);
        Map<String, String[]> map = new LinkedHashMap<String, String[]>();//使用 LinkedHashMap 保证元素的顺序
        for (int i = 0, j = nameAndValueArray.length; i < j; ++i){
            String[] tempArray = nameAndValueArray[i].split("=", 2);

            String key = needEncode ? decodeAndEncode(tempArray[0], charsetType) : tempArray[0];
            String value = tempArray.length == 2 ? tempArray[1] : StringUtils.EMPTY;//有可能参数中,只有名字没有值或者值是空,处理的时候不能遗失掉

            value = needEncode ? decodeAndEncode(value, charsetType) : value;
            map.put(key, ArrayUtils.add(map.get(key), value));
        }
        return map;
    }