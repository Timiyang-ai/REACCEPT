@Override
    public Collection<V> clear() {
        Collection<V> values = new ArrayList<V>(map.values()); // we must create a new list, or the method will always return empty collection.
        map.clear();

        return values;
    }