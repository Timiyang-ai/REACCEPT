public void saveNewFederation() {
        if (newFederation == null) {
            return;
        }

        RepositorySerializer<Federation> serializer = BridgeSerializationUtils::serializeFederationOnlyBtcKeys;

        if (activations.isActive(RSKIP123)) {
            saveStorageVersion(NEW_FEDERATION_FORMAT_VERSION, FEDERATION_FORMAT_VERSION_MULTIKEY);
            serializer = BridgeSerializationUtils::serializeFederation;
        }

        safeSaveToRepository(NEW_FEDERATION_KEY, newFederation, serializer);
    }