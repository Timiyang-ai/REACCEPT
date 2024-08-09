    @Test
    public void createHandshakePacket() {
        char ownSourcePort = (char) 15600;
        long now = System.currentTimeMillis();
        byte[] byteEncodedCooAddress = Hash.NULL_HASH.bytes();
        ByteBuffer buf = Handshake.createHandshakePacket(ownSourcePort, byteEncodedCooAddress, (byte) 1);
        assertEquals("should be of type handshake message", ProtocolMessage.HANDSHAKE.getTypeID(), buf.get());
        int maxLength = ProtocolMessage.HANDSHAKE.getMaxLength();
        assertEquals("should have correct length",
                (maxLength - (maxLength - 60) + Protocol.SUPPORTED_PROTOCOL_VERSIONS.length), buf.getShort());
        assertEquals("should resolve to the correct source port", ownSourcePort, buf.getChar());
        assertTrue("timestamp in handshake packet should be same age or newer than timestamp", now <= buf.getLong());
        byte[] actualCooAddress = new byte[Handshake.BYTE_ENCODED_COO_ADDRESS_BYTES_LENGTH];
        buf.get(actualCooAddress);
        assertArrayEquals("should resolve to the correct coo address", byteEncodedCooAddress, actualCooAddress);
        assertEquals("mwm should be correct", 1, buf.get());
        byte[] supportedVersions = new byte[Protocol.SUPPORTED_PROTOCOL_VERSIONS.length];
        buf.get(supportedVersions);
        assertArrayEquals("should resolve to correct supported protocol versions", Protocol.SUPPORTED_PROTOCOL_VERSIONS,
                supportedVersions);
    }