public void saveFederationElection() {
        if (federationElection == null) {
            return;
        }

        safeSaveToRepository(BRIDGE_FEDERATION_ELECTION_KEY, federationElection, BridgeSerializationUtils::serializeElection);
    }