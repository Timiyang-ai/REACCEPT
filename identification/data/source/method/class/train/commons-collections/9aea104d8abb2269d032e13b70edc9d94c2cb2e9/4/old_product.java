public static <K, V> Properties toProperties(final Map<K, V> map) {
        Properties answer = new Properties();
        if (map != null) {
            for (Iterator<Map.Entry<K, V>> iter = map.entrySet().iterator(); iter.hasNext();) {
                Map.Entry<?, ?> entry = iter.next();
                Object key = entry.getKey();
                Object value = entry.getValue();
                answer.put(key, value);
            }
        }
        return answer;
    }