public static <K, O, V> Map<K, V> constructSubMap(Map<K, O> map,String extractPropertyName,Class<K> keysClass){
        return constructSubMap(map, null, extractPropertyName, keysClass);
    }