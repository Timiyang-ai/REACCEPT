@Override
    public V remove(final Object key) {
        return doWithWriteLock(c -> c.remove(key));
    }