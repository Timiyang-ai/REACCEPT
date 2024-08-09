@Override
    public Collection<V> clear() {
        writeLock.lock();
        try {
            return super.clear();
        } finally {
            writeLock.unlock();
        }
    }