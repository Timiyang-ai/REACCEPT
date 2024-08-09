public static byte[] getSolicitNodeAddress(byte[] targetIp) {
        checkArgument(targetIp.length == Ip6Address.BYTE_LENGTH);
        return new byte[] {
                (byte) 0xff, 0x02, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x01, (byte) 0xff,
                targetIp[targetIp.length - 3],
                targetIp[targetIp.length - 2],
                targetIp[targetIp.length - 1]
        };
    }