public void saveOldFederation() {
        if (shouldSaveOldFederation) {
            RepositorySerializer<Federation> serializer = BridgeSerializationUtils::serializeFederationOnlyBtcKeys;

            if (activations.isActive(RSKIP123)) {
                saveStorageVersion(OLD_FEDERATION_FORMAT_VERSION, FEDERATION_FORMAT_VERSION_MULTIKEY);
                serializer = BridgeSerializationUtils::serializeFederation;
            }

            safeSaveToRepository(OLD_FEDERATION_KEY, oldFederation, serializer);
        }
    }