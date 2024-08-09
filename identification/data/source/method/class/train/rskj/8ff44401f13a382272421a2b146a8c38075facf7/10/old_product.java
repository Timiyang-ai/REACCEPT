public static Federation deserializeFederation(byte[] data, Context btcContext) {
        RLPList rlpList = (RLPList)RLP.decode2(data).get(0);

        if (rlpList.size() != FEDERATION_RLP_LIST_SIZE) {
            throw new RuntimeException(String.format("Invalid serialized Federation. Expected %d elements but got %d", FEDERATION_RLP_LIST_SIZE, rlpList.size()));
        }

        byte[] creationTimeBytes = rlpList.get(FEDERATION_CREATION_TIME_INDEX).getRLPData();
        Instant creationTime = Instant.ofEpochMilli(new BigInteger(creationTimeBytes).longValue());

        List<BtcECKey> pubKeys = ((RLPList) rlpList.get(FEDERATION_PUB_KEYS_INDEX)).stream()
                .map(pubKeyBytes -> BtcECKey.fromPublicOnly(pubKeyBytes.getRLPData()))
                .collect(Collectors.toList());

        byte[] creationBlockNumberBytes = rlpList.get(FEDERATION_CREATION_BLOCK_NUMBER_INDEX).getRLPData();
        long creationBlockNumber = new BigInteger(creationBlockNumberBytes).longValue();

        return new Federation(pubKeys, creationTime, creationBlockNumber, btcContext.getParams());
    }