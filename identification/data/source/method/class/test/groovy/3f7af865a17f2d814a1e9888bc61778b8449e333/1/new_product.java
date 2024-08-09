@Override
    public Map<K, V> clearAll() {
        return doWithWriteLock(c -> c.clearAll());
    }