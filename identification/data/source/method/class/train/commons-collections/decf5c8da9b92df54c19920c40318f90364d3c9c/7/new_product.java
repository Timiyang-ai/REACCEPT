public static <K, V> V getObject(Map<K, V> map, K key, V defaultValue) {
        if (map != null) {
            V answer = map.get(key);
            if (answer != null) {
                return answer;
            }
        }
        return defaultValue;
    }