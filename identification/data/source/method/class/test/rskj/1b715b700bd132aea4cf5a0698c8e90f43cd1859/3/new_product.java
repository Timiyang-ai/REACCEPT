public static PendingFederation deserializePendingFederation(byte[] data) {
        // BTC, RSK and MST keys are the same
        List<FederationMember> members = deserializeBtcPublicKeys(data).stream().map(pk ->
            FederationMember.getFederationMemberFromKey(pk)
        ).collect(Collectors.toList());

        return new PendingFederation(members);
    }