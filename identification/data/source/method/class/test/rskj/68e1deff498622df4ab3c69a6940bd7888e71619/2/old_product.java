public void saveFederationElection() {
        if (shouldSaveFederationElection) {
            DataWord address = new DataWord(BRIDGE_FEDERATION_ELECTION_KEY.getBytes(StandardCharsets.UTF_8));

            byte[] data = null;
            if (federationElection != null)
                data = BridgeSerializationUtils.serializeElection(federationElection);

            repository.addStorageBytes(Hex.decode(contractAddress), address, data);
        }
    }