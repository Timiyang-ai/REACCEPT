public void saveLockWhitelist() {
        if (lockWhitelist == null) {
            return;
        }

        List<LockWhitelistEntry> entries = lockWhitelist.getEntries();

        List<OneOffWhiteListEntry> oneOffEntries = entries
                .stream()
                .filter(e -> e.getClass() == OneOffWhiteListEntry.class)
                .map(e -> (OneOffWhiteListEntry)e)
                .collect(Collectors.toList());
        safeSaveToRepository(LOCK_ONE_OFF_WHITELIST_KEY, Pair.of(oneOffEntries, lockWhitelist.getDisableBlockHeight()), BridgeSerializationUtils::serializeOneOffLockWhitelist);

        if (this.bridgeStorageConfiguration.isUnlimitedWhitelistEnabled()) {
            List<UnlimitedWhiteListEntry> unlimitedEntries = entries
                    .stream()
                    .filter(e -> e.getClass() == UnlimitedWhiteListEntry.class)
                    .map(e -> (UnlimitedWhiteListEntry)e)
                    .collect(Collectors.toList());
            safeSaveToRepository(LOCK_UNLIMITED_WHITELIST_KEY, unlimitedEntries, BridgeSerializationUtils::serializeUnlimitedLockWhitelist);
        }
    }