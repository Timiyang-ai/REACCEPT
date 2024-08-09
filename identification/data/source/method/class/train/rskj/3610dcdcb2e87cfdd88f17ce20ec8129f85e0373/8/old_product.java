public void saveLockWhitelist() {
        if (lockWhitelist == null)
            return;
        safeSaveToRepository(LOCK_WHITELIST_KEY, lockWhitelist, BridgeSerializationUtils::serializeLockWhitelist);
    }