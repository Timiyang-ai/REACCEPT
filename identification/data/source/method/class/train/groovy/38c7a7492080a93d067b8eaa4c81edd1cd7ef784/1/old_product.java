@Override
    public Map<K, V> clear() {
        Map<K, V> result = new LinkedHashMap<K, V>(map.size());

        for (Map.Entry<K, V> entry : map.entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();

            boolean removed = map.remove(key, value);

            if (removed) {
                result.put(key, value);
            }
        }

        return result;
    }