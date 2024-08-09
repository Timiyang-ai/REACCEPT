public static ByteBuffer createHandshakePacket(char ownSourcePort, byte[] ownByteEncodedCooAddress, byte ownUsedMWM) {
        ByteBuffer buf = ByteBuffer
                .allocate(ProtocolMessage.HEADER.getMaxLength() + ProtocolMessage.HANDSHAKE.getMaxLength());
        addProtocolHeader(buf, ProtocolMessage.HANDSHAKE);
        buf.putChar(ownSourcePort);
        buf.putLong(System.currentTimeMillis());
        buf.put(ownByteEncodedCooAddress);
        buf.put(ownUsedMWM);
        buf.flip();
        return buf;
    }