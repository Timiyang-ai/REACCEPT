public void saveNewFederation() {
        if (newFederation == null) {
            return;
        }

        safeSaveToRepository(NEW_FEDERATION_KEY, newFederation, BridgeSerializationUtils::serializeFederationOnlyBtcKeys);
    }