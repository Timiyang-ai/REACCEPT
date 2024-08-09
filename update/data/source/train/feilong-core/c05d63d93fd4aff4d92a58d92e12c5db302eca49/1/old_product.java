public static <K> Map<K, String> toSingleValueMap(Map<K, String[]> arrayValueMap){
        if (Validator.isNullOrEmpty(arrayValueMap)){
            return Collections.emptyMap();
        }
        Map<K, String> singleValueMap = newLinkedHashMap(arrayValueMap.size());//保证顺序和 参数 arrayValueMap 顺序相同
        for (Map.Entry<K, String[]> entry : arrayValueMap.entrySet()){
            singleValueMap.put(entry.getKey(), Validator.isNotNullOrEmpty(entry.getValue()) ? entry.getValue()[0] : StringUtils.EMPTY);
        }
        return singleValueMap;
    }