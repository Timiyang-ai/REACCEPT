public static <K, V> Properties toProperties(final Map<K, V> map) {
        Properties answer = new Properties();
        if (map != null) {
            for (Entry<K, V> entry2 : map.entrySet()) {
                Map.Entry<?, ?> entry = entry2;
                Object key = entry.getKey();
                Object value = entry.getValue();
                answer.put(key, value);
            }
        }
        return answer;
    }