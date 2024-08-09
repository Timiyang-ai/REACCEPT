@Override
    public V get(K key) {
        readLock.lock();
        try {
            return commonCache.get(key);
        } finally {
            readLock.unlock();
        }
    }