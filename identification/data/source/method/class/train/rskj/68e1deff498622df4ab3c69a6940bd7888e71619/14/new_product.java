public void saveFederationElection() {
        if (federationElection == null) {
            return;
        }

        safeSaveToRepository(FEDERATION_ELECTION_KEY, federationElection, BridgeSerializationUtils::serializeElection);
    }