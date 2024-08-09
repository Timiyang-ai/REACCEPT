@Override
    public Collection<V> values() {
        readLock.lock();
        try {
            return map.values();
        } finally {
            readLock.unlock();
        }
    }