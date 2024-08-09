@Override
    public Collection<V> clear() {
        Collection<V> values = map.values();
        map.clear();

        return values;
    }