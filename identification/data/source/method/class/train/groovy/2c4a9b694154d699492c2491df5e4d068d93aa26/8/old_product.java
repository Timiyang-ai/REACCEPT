@Override
    public boolean containsKey(K key) {
        readLock.lock();
        try {
            return commonCache.containsKey(key);
        } finally {
            readLock.unlock();
        }
    }