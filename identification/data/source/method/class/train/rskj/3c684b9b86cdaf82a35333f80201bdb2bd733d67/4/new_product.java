public void savePendingFederation() {
        DataWord address = new DataWord(BRIDGE_PENDING_FEDERATION_KEY.getBytes(StandardCharsets.UTF_8));

        byte[] data = null;
        if (pendingFederation != null)
            data = BridgeSerializationUtils.serializePendingFederation(pendingFederation);

        repository.addStorageBytes(Hex.decode(contractAddress), address, data);
    }