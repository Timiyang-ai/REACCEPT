@Override
    public V put(K key, V value) {
        writeLock.lock();
        try {
            return commonCache.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }