public static <K, T, O> Map<K, O> constructSubMap(Map<K, T> map,String extractPropertyName){
        return constructSubMap(map, null, extractPropertyName);
    }