public void savePendingFederation() {
        if (shouldSavePendingFederation) {
            safeSaveToRepository(BRIDGE_PENDING_FEDERATION_KEY, pendingFederation, BridgeSerializationUtils::serializePendingFederation);
        }
    }