@Override
    public V get(final K key) {
        return doWithReadLock(c -> c.get(key));
    }