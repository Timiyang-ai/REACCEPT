public void saveLockWhitelist() {
        if (lockWhitelist == null) {
            return;
        }

        safeSaveToRepository(LOCK_ONE_OFF_WHITELIST_KEY, lockWhitelist, BridgeSerializationUtils::serializeOneOffLockWhitelist);

        if (this.bridgeStorageConfiguration.isUnlimitedWhitelistEnabled()) {
            safeSaveToRepository(LOCK_UNLIMITED_WHITELIST_KEY, lockWhitelist, BridgeSerializationUtils::serializeUnlimitedLockWhitelist);
        }
    }