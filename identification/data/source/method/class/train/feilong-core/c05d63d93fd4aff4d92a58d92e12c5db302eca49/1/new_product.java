public static <K, V> Map<K, V> toSingleValueMap(Map<K, V[]> arrayValueMap){
        if (Validator.isNullOrEmpty(arrayValueMap)){
            return Collections.emptyMap();
        }
        Map<K, V> singleValueMap = newLinkedHashMap(arrayValueMap.size());//保证顺序和 参数 arrayValueMap 顺序相同
        for (Map.Entry<K, V[]> entry : arrayValueMap.entrySet()){
            singleValueMap.put(entry.getKey(), null == entry.getValue() ? null : entry.getValue()[0]);
        }
        return singleValueMap;
    }