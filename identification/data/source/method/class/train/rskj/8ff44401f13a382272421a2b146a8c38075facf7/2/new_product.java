public static Federation deserializeFederation(byte[] data, Context btcContext) {
        RLPList rlpList = (RLPList)RLP.decode2(data).get(0);

        if (rlpList.size() != 3) {
            throw new RuntimeException(String.format("Invalid serialized Federation. Expected 3 elements but got {}", rlpList.size()));
        }

        byte[] creationTimeBytes = rlpList.get(0).getRLPData();
        if (creationTimeBytes.length != 8) {
            throw new RuntimeException(String.format("Invalid serialized Federation creation time. Expected 8 bytes but got {}", creationTimeBytes.length));
        }
        Instant creationTime = Instant.ofEpochMilli(new BigInteger(creationTimeBytes).longValue());

        byte[] numberOfSignaturesRequiredBytes = rlpList.get(1).getRLPData();
        if (numberOfSignaturesRequiredBytes.length != 4) {
            throw new RuntimeException(String.format("Invalid serialized Federation # of signatures required. Expected 4 bytes but got {}", numberOfSignaturesRequiredBytes.length));
        }
        int numberOfSignaturesRequired =  new BigInteger(creationTimeBytes).intValue();

        List<BtcECKey> pubKeys = ((RLPList) rlpList.get(2)).stream()
                .map(pubKeyBytes -> BtcECKey.fromPublicOnly(pubKeyBytes.getRLPData()))
                .collect(Collectors.toList());

        if (pubKeys.size() < numberOfSignaturesRequired) {
            throw new RuntimeException(String.format("Invalid serialized Federation # of public keys. Expected at least {} but got {}", numberOfSignaturesRequired, pubKeys.size()));
        }

        return new Federation(numberOfSignaturesRequired, pubKeys, creationTime, btcContext.getParams());
    }