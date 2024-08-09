static String createBinaryString(IpPrefix ip4Prefix) {
        if (ip4Prefix.prefixLength() == 0) {
            return "";
        }

        StringBuilder result = new StringBuilder(ip4Prefix.prefixLength());
        long value = ip4Prefix.address().toInt() & 0xffffffffL;
        for (int i = 0; i < ip4Prefix.prefixLength(); i++) {
            long mask = 1 << (IpPrefix.MAX_INET_MASK_LENGTH - 1 - i);
            result.append(((value & mask) == 0) ? "0" : "1");
        }
        return result.toString();
    }