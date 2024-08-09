public static Federation deserializeFederation(byte[] data, Context btcContext) {
        RLPList rlpList = (RLPList)RLP.decode2(data).get(0);

        if (rlpList.size() != 2) {
            throw new RuntimeException(String.format("Invalid serialized Federation. Expected 2 elements but got %d", rlpList.size()));
        }

        byte[] creationTimeBytes = rlpList.get(0).getRLPData();
        Instant creationTime = Instant.ofEpochMilli(new BigInteger(creationTimeBytes).longValue());

        List<BtcECKey> pubKeys = ((RLPList) rlpList.get(1)).stream()
                .map(pubKeyBytes -> BtcECKey.fromPublicOnly(pubKeyBytes.getRLPData()))
                .collect(Collectors.toList());

        return new Federation(pubKeys, creationTime, btcContext.getParams());
    }