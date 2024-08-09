@SafeVarargs
    public static <V, K> Map<K, V> toMap(Map.Entry<K, V>...mapEntrys){
        if (null == mapEntrys){
            return emptyMap();
        }
        Validate.noNullElements(mapEntrys, "mapEntrys can't has null elememt!");

        Map<K, V> map = newLinkedHashMap(mapEntrys.length);
        for (Map.Entry<K, V> entry : mapEntrys){
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }