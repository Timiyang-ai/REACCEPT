@Override
    public int size() {
        readLock.lock();
        try {
            return commonCache.size();
        } finally {
            readLock.unlock();
        }
    }