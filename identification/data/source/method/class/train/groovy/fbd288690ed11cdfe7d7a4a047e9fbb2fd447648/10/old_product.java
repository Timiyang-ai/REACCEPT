@Override
    public Map<K, V> clear() {
        Map<K, V> result = new LinkedHashMap<K, V>(map);
        map.clear();

        return result;
    }