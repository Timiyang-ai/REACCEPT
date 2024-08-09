@SafeVarargs
    public static <K, T> Map<K, T> getSubMapExcludeKeys(Map<K, T> map,K...excludeKeys){
        if (Validator.isNullOrEmpty(map)){
            return Collections.emptyMap();
        }
        if (Validator.isNullOrEmpty(excludeKeys)){
            return map;
        }

        Map<K, T> returnMap = new LinkedHashMap<K, T>(map);

        for (K key : excludeKeys){
            if (map.containsKey(key)){
                returnMap.remove(key);
            }else{
                if (LOGGER.isWarnEnabled()){
                    LOGGER.warn("map:{} don't contains key:[{}]", JsonUtil.format(map), key);
                }
            }
        }
        return returnMap;
    }