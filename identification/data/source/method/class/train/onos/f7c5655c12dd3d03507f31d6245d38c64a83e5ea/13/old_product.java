public static byte[] addMetadata(byte[] ospfPacket, int allDroutersValue, Ip4Address destinationIp) {
        byte[] packet;
        byte[] allDroutersByteVal = {(byte) allDroutersValue};
        byte[] destIpAsBytes = destinationIp.toOctets();
        byte[] metadata = Bytes.concat(allDroutersByteVal, destIpAsBytes);

        packet = Bytes.concat(metadata, ospfPacket);

        return packet;
    }