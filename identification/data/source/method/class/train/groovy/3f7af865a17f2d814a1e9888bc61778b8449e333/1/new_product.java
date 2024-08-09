@Override
    public Map<K, V> clear() {
        writeLock.lock();
        try {
            return super.clear();
        } finally {
            writeLock.unlock();
        }
    }