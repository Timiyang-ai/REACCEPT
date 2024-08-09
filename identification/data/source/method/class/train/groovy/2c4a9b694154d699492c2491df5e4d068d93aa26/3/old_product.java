@Override
    public Collection<V> values() {
        readLock.lock();
        try {
            return commonCache.values();
        } finally {
            readLock.unlock();
        }
    }