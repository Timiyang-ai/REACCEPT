@Override
    public Map<K, V> clear() {
        writeLock.lock();
        try {
            return commonCache.clear();
        } finally {
            writeLock.unlock();
        }
    }