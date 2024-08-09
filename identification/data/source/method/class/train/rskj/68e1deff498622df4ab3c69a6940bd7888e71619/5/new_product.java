public void saveFederationElection() {
        if (federationElection == null)
            return;

        byte[] data = BridgeSerializationUtils.serializeElection(federationElection);
        repository.addStorageBytes(contractAddress, BRIDGE_FEDERATION_ELECTION_KEY, data);
    }