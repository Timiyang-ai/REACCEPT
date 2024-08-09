@Override
    public Collection<V> values() {
        return doWithReadLock(c -> c.values());
    }