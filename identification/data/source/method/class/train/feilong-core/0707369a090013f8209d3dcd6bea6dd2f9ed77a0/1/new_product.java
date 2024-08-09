public static <K, V> Map<K, V> sortByKeyAsc(Map<K, V> map){
        if (null == map){
            return emptyMap();
        }
        return sort(map, new PropertyComparator<Map.Entry<K, V>>("key"));
    }