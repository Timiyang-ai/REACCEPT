@Override
    public V get(final Object key) {
        return doWithReadLock(c -> c.get(key));
    }