public static byte[] serializeLockWhitelist(LockWhitelist whitelist) {
        List<Address> whitelistAddresses = whitelist.getAddresses();
        byte[][] serializedLockWhitelist = new byte[whitelistAddresses.size() * 2][];
        for (int i = 0; i < whitelistAddresses.size(); i++) {
            Address address = whitelistAddresses.get(i);
            serializedLockWhitelist[2 * i] = RLP.encodeElement(address.getHash160());
            serializedLockWhitelist[2 * i + 1] = RLP.encodeBigInteger(BigInteger.valueOf(whitelist.getMaxTransferValue(address).longValue()));
        }
        return RLP.encodeList(serializedLockWhitelist);
    }