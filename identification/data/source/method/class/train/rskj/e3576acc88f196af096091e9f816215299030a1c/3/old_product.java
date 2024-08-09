public static byte[] serializeFederation(Federation federation) {
        List<byte[]> publicKeys = federation.getPublicKeys().stream()
                .map(key -> key.getPubKey())
                .collect(Collectors.toList());
        return RLP.encodeList(
                RLP.encodeBigInteger(BigInteger.valueOf(federation.getCreationTime())),
                RLP.encodeBigInteger(BigInteger.valueOf(federation.getNumberOfSignaturesRequired())),
                RLP.encodeList((byte[][])publicKeys.toArray())
        );
    }