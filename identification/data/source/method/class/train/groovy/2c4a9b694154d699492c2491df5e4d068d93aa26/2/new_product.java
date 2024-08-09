@Override
    public V remove(Object key) {
        writeLock.lock();
        try {
            return commonCache.remove(key);
        } finally {
            writeLock.unlock();
        }
    }