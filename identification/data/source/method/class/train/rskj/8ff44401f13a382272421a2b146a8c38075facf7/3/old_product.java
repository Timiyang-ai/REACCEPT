public static Federation deserializeFederation(byte[] data, NetworkParameters networkParameters) {
        RLPList rlpList = (RLPList)RLP.decode2(data).get(0);

        if (rlpList.size() != FEDERATION_RLP_LIST_SIZE) {
            throw new RuntimeException(String.format("Invalid serialized Federation. Expected %d elements but got %d", FEDERATION_RLP_LIST_SIZE, rlpList.size()));
        }

        byte[] creationTimeBytes = rlpList.get(FEDERATION_CREATION_TIME_INDEX).getRLPData();
        Instant creationTime = Instant.ofEpochMilli(BigIntegers.fromUnsignedByteArray(creationTimeBytes).longValue());

        // IMPORTANT: Both BTC and RSK public keys are the same.
        // This is for compatibility with the pre <INSERT FORK NAME HERE> fork network.
        List<FederationMember> federationMembers = ((RLPList) rlpList.get(FEDERATION_PUB_KEYS_INDEX)).stream()
                .map(pubKeyBytes -> new FederationMember(
                        BtcECKey.fromPublicOnly(pubKeyBytes.getRLPData()),
                        ECKey.fromPublicOnly(pubKeyBytes.getRLPData())
                )).collect(Collectors.toList());

        List<ECKey> rskPubKeys = ((RLPList) rlpList.get(FEDERATION_PUB_KEYS_INDEX)).stream()
                .map(pubKeyBytes -> ECKey.fromPublicOnly(pubKeyBytes.getRLPData()))
                .collect(Collectors.toList());

        byte[] creationBlockNumberBytes = rlpList.get(FEDERATION_CREATION_BLOCK_NUMBER_INDEX).getRLPData();
        long creationBlockNumber = BigIntegers.fromUnsignedByteArray(creationBlockNumberBytes).longValue();

        return new Federation(federationMembers, creationTime, creationBlockNumber, networkParameters);
    }