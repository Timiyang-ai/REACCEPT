public boolean matchesRelevantPubKey(ProtectedStorageEntry protectedStorageEntry) {
        boolean result = protectedStorageEntry.getOwnerPubKey().equals(this.ownerPubKey);

        if (!result) {
            log.warn("New data entry does not match our stored data. storedData.ownerPubKey=" +
                    (protectedStorageEntry.getOwnerPubKey() != null ? protectedStorageEntry.getOwnerPubKey().toString() : "null") +
                    ", ownerPubKey=" + this.ownerPubKey);
        }

        return result;
    }