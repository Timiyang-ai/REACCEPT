public static <K, V> Map<K, V> toMap(K key1,V value1,K key2,V value2){
        Map<K, V> map = toMap(key1, value1);
        map.put(key2, value2);
        return map;
    }