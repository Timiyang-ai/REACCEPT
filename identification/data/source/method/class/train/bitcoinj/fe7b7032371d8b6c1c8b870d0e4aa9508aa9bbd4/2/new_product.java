public boolean isWatching() {
        keyChainGroupLock.lock();
        try {
            return keyChainGroup.isWatching();
        } finally {
            keyChainGroupLock.unlock();
        }
    }