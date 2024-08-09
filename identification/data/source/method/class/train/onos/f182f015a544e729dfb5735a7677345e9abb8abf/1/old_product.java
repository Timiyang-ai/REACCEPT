public static Ip4Address makeMaskedAddress(final Ip4Address addr,
                                               int prefixLen) {
        Ip4Address mask = Ip4Address.makeMaskPrefix(prefixLen);
        long v = addr.value & mask.value;

        return new Ip4Address((int) v);
    }