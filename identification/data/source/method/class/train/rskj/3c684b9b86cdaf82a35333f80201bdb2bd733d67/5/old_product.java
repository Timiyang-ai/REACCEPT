public void savePendingFederation() {
        if (shouldSavePendingFederation) {
            RepositorySerializer<PendingFederation> serializer = BridgeSerializationUtils::serializePendingFederationOnlyBtcKeys;

            if (bridgeStorageConfiguration.isMultikeyFederation()) {
                saveStorageVersion(PENDING_FEDERATION_FORMAT_VERSION, FEDERATION_FORMAT_VERSION_MULTIKEY);
                serializer = BridgeSerializationUtils::serializePendingFederation;
            }

            safeSaveToRepository(PENDING_FEDERATION_KEY, pendingFederation, serializer);
        }
    }