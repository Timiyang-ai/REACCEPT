@Override
    public Set<K> keys() {
        return doWithReadLock(c -> c.keys());
    }