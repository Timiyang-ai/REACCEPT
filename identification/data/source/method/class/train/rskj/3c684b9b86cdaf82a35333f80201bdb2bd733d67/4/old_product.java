public void savePendingFederation() {
        if (pendingFederation == null)
            return;

        byte[] data = BridgeSerializationUtils.serializePendingFederation(pendingFederation);

        DataWord address = new DataWord(BRIDGE_PENDING_FEDERATION_KEY.getBytes(StandardCharsets.UTF_8));

        repository.addStorageBytes(Hex.decode(contractAddress), address, data);
    }