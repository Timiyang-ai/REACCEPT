public static Map<String, String[]> toSafeArrayValueMap(String queryString,String charsetType){
        if (Validator.isNullOrEmpty(queryString)){
            return Collections.emptyMap();
        }

        String[] nameAndValueArray = queryString.split(URIComponents.AMPERSAND);
        if (Validator.isNullOrEmpty(nameAndValueArray)){
            return Collections.emptyMap();
        }

        //使用 LinkedHashMap 保证元素的顺序
        Map<String, String[]> map = new LinkedHashMap<String, String[]>();
        for (int i = 0, j = nameAndValueArray.length; i < j; ++i){
            String nameAndValue = nameAndValueArray[i];
            if (null == nameAndValue){
                continue;
            }
            String[] tempArray = nameAndValue.split("=", 2);

            String key = tempArray[0];
            String value = tempArray.length == 2 ? tempArray[1] : StringUtils.EMPTY;//有可能 参数中 只有名字没有值 或者值是空,处理的时候不能遗失掉

            if (Validator.isNotNullOrEmpty(charsetType)){
                key = decodeAndEncode(key, charsetType);
                value = decodeAndEncode(value, charsetType);
            }
            String[] valuesArrayInMap = map.get(key);

            List<String> list = null == valuesArrayInMap ? new ArrayList<String>() : ConvertUtil.toList(valuesArrayInMap);
            list.add(value);

            map.put(key, ConvertUtil.toArray(list, String.class));
        }
        return map;
    }