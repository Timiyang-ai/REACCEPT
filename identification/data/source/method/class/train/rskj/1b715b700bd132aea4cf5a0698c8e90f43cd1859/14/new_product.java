public static PendingFederation deserializePendingFederation(byte[] data) {
        RLPList rlpList = (RLPList)RLP.decode2(data).get(0);

        List<BtcECKey> pubKeys = rlpList.stream()
                .map(pubKeyBytes -> BtcECKey.fromPublicOnly(pubKeyBytes.getRLPData()))
                .collect(Collectors.toList());

        return new PendingFederation(pubKeys);
    }