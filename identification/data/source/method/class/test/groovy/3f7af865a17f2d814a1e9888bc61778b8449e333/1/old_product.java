@Override
    public Map<K, V> clear() {
        return doWithWriteLock(c -> c.clear());
    }