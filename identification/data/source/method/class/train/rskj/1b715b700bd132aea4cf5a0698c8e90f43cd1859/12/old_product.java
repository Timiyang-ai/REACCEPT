public static PendingFederation deserializePendingFederation(byte[] data) {
        RLPList rlpList = (RLPList)RLP.decode2(data).get(0);

        if (rlpList.size() != 4) {
            throw new RuntimeException(String.format("Invalid serialized PendingFederation. Expected 4 elements but got %d", rlpList.size()));
        }

        byte[] idBytes = rlpList.get(0).getRLPData();
        int id = new BigInteger(idBytes).intValue();

        byte[] numberOfPublicKeysRequiredBytes = rlpList.get(2).getRLPData();
        int numberOfPublicKeysRequired =  new BigInteger(numberOfPublicKeysRequiredBytes).intValue();
        if (numberOfPublicKeysRequired < 1) {
            throw new RuntimeException(String.format("Invalid serialized PendingFederation # of public keys required. Expected at least 1, but got %d", numberOfPublicKeysRequired));
        }

        byte[] numberOfSignaturesRequiredBytes = rlpList.get(1).getRLPData();
        int numberOfSignaturesRequired =  new BigInteger(numberOfSignaturesRequiredBytes).intValue();
        if (numberOfSignaturesRequired < 1) {
            throw new RuntimeException(String.format("Invalid serialized PendingFederation # of signatures required. Expected at least 1, but got %d", numberOfSignaturesRequired));
        }

        if (numberOfPublicKeysRequired < numberOfSignaturesRequired) {
            throw new RuntimeException(String.format("Invalid serialized PendingFederation # of signatures required. Number of public keys required (%d) must be greater or equal than number of signatures required (%d)", numberOfPublicKeysRequired, numberOfSignaturesRequired));
        }

        List<BtcECKey> pubKeys = ((RLPList) rlpList.get(3)).stream()
                .map(pubKeyBytes -> BtcECKey.fromPublicOnly(pubKeyBytes.getRLPData()))
                .collect(Collectors.toList());

        if (pubKeys.size() > numberOfPublicKeysRequired) {
            throw new RuntimeException(String.format("Invalid serialized PendingFederation # of public keys. Expected at most %d but got %d", numberOfPublicKeysRequired, pubKeys.size()));
        }

        return new PendingFederation(id, numberOfSignaturesRequired, numberOfPublicKeysRequired, pubKeys);
    }