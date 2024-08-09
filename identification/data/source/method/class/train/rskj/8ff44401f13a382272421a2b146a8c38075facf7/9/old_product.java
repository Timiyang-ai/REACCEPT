public static Federation deserializeFederation(byte[] data, Context btcContext) {
        RLPList rlpList = (RLPList)RLP.decode2(data).get(0);

        if (rlpList.size() != 3) {
            throw new RuntimeException(String.format("Invalid serialized Federation. Expected 3 elements but got %d", rlpList.size()));
        }

        byte[] creationTimeBytes = rlpList.get(0).getRLPData();
        Instant creationTime = Instant.ofEpochMilli(new BigInteger(creationTimeBytes).longValue());

        byte[] numberOfSignaturesRequiredBytes = rlpList.get(1).getRLPData();
        int numberOfSignaturesRequired =  new BigInteger(numberOfSignaturesRequiredBytes).intValue();
        if (numberOfSignaturesRequired < 1) {
            throw new RuntimeException(String.format("Invalid serialized Federation # of signatures required. Expected at least 1, but got %d", numberOfSignaturesRequired));
        }

        List<BtcECKey> pubKeys = ((RLPList) rlpList.get(2)).stream()
                .map(pubKeyBytes -> BtcECKey.fromPublicOnly(pubKeyBytes.getRLPData()))
                .collect(Collectors.toList());

        if (pubKeys.size() < numberOfSignaturesRequired) {
            throw new RuntimeException(String.format("Invalid serialized Federation # of public keys. Expected at least %d but got %d", numberOfSignaturesRequired, pubKeys.size()));
        }

        return new Federation(numberOfSignaturesRequired, pubKeys, creationTime, btcContext.getParams());
    }