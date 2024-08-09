public static byte[] serializeFederation(Federation federation) {
        List<byte[]> publicKeys = federation.getPublicKeys().stream()
                .sorted(BtcECKey.PUBKEY_COMPARATOR)
                .map(key -> key.getPubKey())
                .collect(Collectors.toList());
        return RLP.encodeList(
                RLP.encodeBigInteger(BigInteger.valueOf(federation.getCreationTime().toEpochMilli())),
                RLP.encodeBigInteger(BigInteger.valueOf(federation.getNumberOfSignaturesRequired())),
                RLP.encodeList((byte[][])publicKeys.toArray())
        );
    }