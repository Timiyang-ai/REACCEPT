@Override
    public void cleanUpNullReferences() {
        writeLock.lock();
        try {
            commonCache.cleanUpNullReferences();
        } finally {
            writeLock.unlock();
        }
    }