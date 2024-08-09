@Override
    public V remove(K key) {
        writeLock.lock();
        try {
            return commonCache.remove(key);
        } finally {
            writeLock.unlock();
        }
    }