public static PendingFederation deserializePendingFederation(byte[] data) {
        // BTC and RSK keys are the same
        List<FederationMember> members = deserializeBtcPublicKeys(data).stream().map(pk ->
            new FederationMember(pk, ECKey.fromPublicOnly(pk.getPubKey()))
        ).collect(Collectors.toList());

        return new PendingFederation(members);
    }