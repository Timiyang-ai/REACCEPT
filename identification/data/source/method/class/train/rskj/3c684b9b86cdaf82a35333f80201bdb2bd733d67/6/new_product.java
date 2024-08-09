public void savePendingFederation() {
        if (shouldSavePendingFederation) {
            safeSaveToRepository(PENDING_FEDERATION_KEY, pendingFederation, BridgeSerializationUtils::serializePendingFederation);
        }
    }