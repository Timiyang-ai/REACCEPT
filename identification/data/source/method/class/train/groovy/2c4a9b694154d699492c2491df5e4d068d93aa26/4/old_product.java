@Override
    public Set<K> keys() {
        readLock.lock();
        try {
            return super.keys();
        } finally {
            readLock.unlock();
        }
    }