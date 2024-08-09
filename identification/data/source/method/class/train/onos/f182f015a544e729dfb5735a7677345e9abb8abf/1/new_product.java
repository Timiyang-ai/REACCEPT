public static Ip4Address makeMaskedAddress(final Ip4Address address,
                                               int prefixLength) {
        byte[] net = makeMaskedAddressArray(address, prefixLength);
        return Ip4Address.valueOf(net);
    }