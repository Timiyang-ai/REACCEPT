public static PendingFederation deserializePendingFederation(byte[] data) {
        RLPList rlpList = (RLPList)RLP.decode2(data).get(0);

        if (rlpList.size() != 3) {
            throw new RuntimeException(String.format("Invalid serialized PendingFederation. Expected 3 elements but got %d", rlpList.size()));
        }

        byte[] idBytes = rlpList.get(0).getRLPData();
        int id = new BigInteger(idBytes).intValue();

        byte[] numberOfSignaturesRequiredBytes = rlpList.get(1).getRLPData();
        int numberOfSignaturesRequired =  new BigInteger(numberOfSignaturesRequiredBytes).intValue();
        if (numberOfSignaturesRequired < 1) {
            throw new RuntimeException(String.format("Invalid serialized PendingFederation # of signatures required. Expected at least 1, but got %d", numberOfSignaturesRequired));
        }

        List<BtcECKey> pubKeys = ((RLPList) rlpList.get(2)).stream()
                .map(pubKeyBytes -> BtcECKey.fromPublicOnly(pubKeyBytes.getRLPData()))
                .collect(Collectors.toList());

        return new PendingFederation(id, numberOfSignaturesRequired, pubKeys);
    }