public static byte[] serializePendingFederation(PendingFederation pendingFederation) {
        List<byte[]> publicKeys = pendingFederation.getPublicKeys().stream()
                .sorted(BtcECKey.PUBKEY_COMPARATOR)
                .map(key -> key.getPubKey())
                .collect(Collectors.toList());
        return RLP.encodeList(
                RLP.encodeBigInteger(BigInteger.valueOf(pendingFederation.getId())),
                RLP.encodeBigInteger(BigInteger.valueOf(pendingFederation.getNumberOfPublicKeysRequired())),
                RLP.encodeBigInteger(BigInteger.valueOf(pendingFederation.getNumberOfSignaturesRequired())),
                RLP.encodeList((byte[][])publicKeys.toArray(new byte[publicKeys.size()][]))
        );
    }