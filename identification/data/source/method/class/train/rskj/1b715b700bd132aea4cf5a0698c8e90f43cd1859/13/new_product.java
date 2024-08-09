public static PendingFederation deserializePendingFederation(byte[] data) {
        RLPList rlpList = (RLPList)RLP.decode2(data).get(0);

        List<FederationMember> members = rlpList.stream()
                .map(memberBytes -> deserializeFederationMember(memberBytes.getRLPData()))
                .collect(Collectors.toList());

        return new PendingFederation(members);
    }