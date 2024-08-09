@Override
    public V put(final K key, final V value) {
        return doWithWriteLock(c -> c.put(key, value));
    }