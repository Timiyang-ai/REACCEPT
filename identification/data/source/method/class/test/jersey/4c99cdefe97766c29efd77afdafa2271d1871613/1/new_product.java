@Override
    public Set<Map.Entry<K, V>> entrySet() {
        final Set<Map.Entry<K, V>> es = entrySet;
        return (es != null ? es : (entrySet = (Set<Map.Entry<K, V>>) new EntrySet()));
    }