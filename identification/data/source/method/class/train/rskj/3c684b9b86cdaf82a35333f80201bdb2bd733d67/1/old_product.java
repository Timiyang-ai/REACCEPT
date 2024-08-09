public void savePendingFederation() {
        if (shouldSavePendingFederation) {
            byte[] data = null;
            if (pendingFederation != null)
                data = BridgeSerializationUtils.serializePendingFederation(pendingFederation);

            repository.addStorageBytes(contractAddress, BRIDGE_PENDING_FEDERATION_KEY, data);
        }
    }