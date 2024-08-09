@Override
    public void cleanUpNullReferences() {
        writeLock.lock();
        try {
            super.cleanUpNullReferences();
        } finally {
            writeLock.unlock();
        }
    }