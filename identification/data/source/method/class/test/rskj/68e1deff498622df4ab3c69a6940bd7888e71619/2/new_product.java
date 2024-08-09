public void saveFederationElection() {
        if (federationElection == null)
            return;

        DataWord address = new DataWord(BRIDGE_FEDERATION_ELECTION_KEY.getBytes(StandardCharsets.UTF_8));

        byte[] data = BridgeSerializationUtils.serializeElection(federationElection);

        repository.addStorageBytes(Hex.decode(contractAddress), address, data);
    }