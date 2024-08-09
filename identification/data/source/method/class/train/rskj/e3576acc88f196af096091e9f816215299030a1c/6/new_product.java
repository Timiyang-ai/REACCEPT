public static byte[] serializeFederation(Federation federation) {
        List<byte[]> publicKeys = federation.getPublicKeys().stream()
                .sorted(BtcECKey.PUBKEY_COMPARATOR)
                .map(key -> RLP.encodeElement(key.getPubKey()))
                .collect(Collectors.toList());
        return RLP.encodeList(
                RLP.encodeBigInteger(BigInteger.valueOf(federation.getCreationTime().toEpochMilli())),
                RLP.encodeList((byte[][])publicKeys.toArray(new byte[publicKeys.size()][]))
        );
    }