public static byte[] serializeFederation(Federation federation) {
        List<byte[]> publicKeys = federation.getPublicKeys().stream()
                .sorted(BtcECKey.PUBKEY_COMPARATOR)
                .map(key -> RLP.encodeElement(key.getPubKey()))
                .collect(Collectors.toList());
        byte[][] rlpElements = new byte[FEDERATION_RLP_LIST_SIZE][];
        rlpElements[FEDERATION_CREATION_TIME_INDEX] = RLP.encodeBigInteger(BigInteger.valueOf(federation.getCreationTime().toEpochMilli()));
        rlpElements[FEDERATION_PUB_KEYS_INDEX] = RLP.encodeList((byte[][])publicKeys.toArray(new byte[publicKeys.size()][]));
        return RLP.encodeList(rlpElements);
    }