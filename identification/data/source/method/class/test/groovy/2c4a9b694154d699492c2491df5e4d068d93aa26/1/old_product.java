@Override
    public V remove(final K key) {
        return doWithWriteLock(c -> c.remove(key));
    }