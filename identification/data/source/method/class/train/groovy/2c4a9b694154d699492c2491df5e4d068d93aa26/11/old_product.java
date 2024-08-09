@Override
    public Set<K> keys() {
        readLock.lock();
        try {
            return commonCache.keys();
        } finally {
            readLock.unlock();
        }
    }