public static <K, V> Map<K, V> sortByKeyAsc(Map<K, V> map){
        if (null == map){
            return emptyMap();
        }
        return new TreeMap<K, V>(map);
    }