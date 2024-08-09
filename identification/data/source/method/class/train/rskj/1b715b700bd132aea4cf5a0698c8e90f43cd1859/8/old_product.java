public static byte[] serializePendingFederation(PendingFederation pendingFederation) {
        List<byte[]> publicKeys = pendingFederation.getPublicKeys().stream()
                .sorted(BtcECKey.PUBKEY_COMPARATOR)
                .map(key -> RLP.encodeElement(key.getPubKey()))
                .collect(Collectors.toList());
        return RLP.encodeList((byte[][])publicKeys.toArray(new byte[publicKeys.size()][]));
    }