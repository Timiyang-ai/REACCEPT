public boolean isWatching() {
        keyChainGroupLock.lock();
        try {
            maybeUpgradeToHD();
            return keyChainGroup.isWatching();
        } finally {
            keyChainGroupLock.unlock();
        }
    }