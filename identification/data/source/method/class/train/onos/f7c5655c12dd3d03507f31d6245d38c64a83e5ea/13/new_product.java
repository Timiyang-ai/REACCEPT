public static byte[] addMetadata(int interfaceIndex, byte[] ospfPacket, int allDroutersValue,
                                     Ip4Address destinationIp) {
        byte[] packet;
        byte[] interfaceIndexByteVal = {(byte) interfaceIndex};
        byte[] allDroutersByteVal = {(byte) allDroutersValue};
        byte[] destIpAsBytes = destinationIp.toOctets();
        byte[] metadata = Bytes.concat(interfaceIndexByteVal, allDroutersByteVal);
        metadata = Bytes.concat(metadata, destIpAsBytes);
        packet = Bytes.concat(ospfPacket, metadata);

        return packet;
    }