@Override
    public V get(Object key) {
        readLock.lock();
        try {
            return commonCache.get(key);
        } finally {
            readLock.unlock();
        }
    }