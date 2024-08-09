public void saveOldFederation() {
        if (shouldSaveOldFederation) {
            safeSaveToRepository(OLD_FEDERATION_KEY, oldFederation, BridgeSerializationUtils::serializeFederationOnlyBtcKeys);
        }
    }