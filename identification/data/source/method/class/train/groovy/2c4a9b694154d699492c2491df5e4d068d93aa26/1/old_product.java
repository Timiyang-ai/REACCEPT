@Override
    public boolean containsKey(final K key) {
        return doWithReadLock(c -> c.containsKey(key));
    }