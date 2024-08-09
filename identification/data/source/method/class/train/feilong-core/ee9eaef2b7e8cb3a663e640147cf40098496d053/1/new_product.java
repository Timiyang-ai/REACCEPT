public static <K, O, V> Map<K, V> extractSubMap(Map<K, O> map,K[] includeKeys,String extractPropertyName,Class<K> keysClass){
        if (Validator.isNullOrEmpty(map)){
            throw new NullPointerException("the map is null or empty!");
        }
        if (Validator.isNullOrEmpty(extractPropertyName)){
            throw new NullPointerException("extractPropertyName is null or empty!");
        }
        //如果excludeKeys 是null ,那么抽取 所有的key
        if (Validator.isNullOrEmpty(includeKeys)){
            Set<K> keySet = map.keySet();
            includeKeys = CollectionsUtil.toArray(keySet, keysClass);
        }

        Map<K, V> returnMap = new HashMap<K, V>();

        for (K key : includeKeys){
            if (map.containsKey(key)){
                O o = map.get(key);
                V v = PropertyUtil.getProperty(o, extractPropertyName);
                returnMap.put(key, v);
            }else{
                LOGGER.warn("map don't contains key:[{}]", key);
            }
        }
        return returnMap;
    }