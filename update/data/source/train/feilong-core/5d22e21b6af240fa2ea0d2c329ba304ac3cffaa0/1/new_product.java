public static <K, O, V> Map<K, V> extractSubMap(Map<K, O> map,String extractPropertyName){
        return extractSubMap(map, null, extractPropertyName);
    }