public static <K, O, V> Map<K, V> extractSubMap(Map<K, O> map,String extractPropertyName,Class<K> keysClass){
        return extractSubMap(map, null, extractPropertyName, keysClass);
    }