public static byte[] serializePendingFederation(PendingFederation pendingFederation) {
        List<byte[]> encodedMembers = pendingFederation.getMembers().stream()
                .sorted(FederationMember.BTC_RSK_MST_PUBKEYS_COMPARATOR)
                .map(BridgeSerializationUtils::serializeFederationMember)
                .collect(Collectors.toList());
        return RLP.encodeList(encodedMembers.toArray(new byte[0][]));
    }