@Override
    public boolean containsKey(Object key) {
        readLock.lock();
        try {
            return commonCache.containsKey(key);
        } finally {
            readLock.unlock();
        }
    }