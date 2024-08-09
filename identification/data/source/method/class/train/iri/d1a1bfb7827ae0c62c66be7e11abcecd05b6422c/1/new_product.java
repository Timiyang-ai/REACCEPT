public static ByteBuffer createHandshakePacket(char ownSourcePort, byte[] ownByteEncodedCooAddress,
            byte ownUsedMWM) {
        short maxLength = ProtocolMessage.HANDSHAKE.getMaxLength();
        final short payloadLengthBytes = (short) (maxLength - (maxLength - 60) + SUPPORTED_PROTOCOL_VERSIONS.length);
        ByteBuffer buf = ByteBuffer.allocate(ProtocolMessage.HEADER.getMaxLength() + payloadLengthBytes);
        addProtocolHeader(buf, ProtocolMessage.HANDSHAKE, payloadLengthBytes);
        buf.putChar(ownSourcePort);
        buf.putLong(System.currentTimeMillis());
        buf.put(ownByteEncodedCooAddress);
        buf.put(ownUsedMWM);
        buf.put(SUPPORTED_PROTOCOL_VERSIONS);
        buf.flip();
        return buf;
    }