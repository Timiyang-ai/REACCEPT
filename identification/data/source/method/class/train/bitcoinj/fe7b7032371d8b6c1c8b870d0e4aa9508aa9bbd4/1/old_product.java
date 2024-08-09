public boolean isWatching() {
        keychainLock.lock();
        try {
            maybeUpgradeToHD();
            return keychain.isWatching();
        } finally {
            keychainLock.unlock();
        }
    }