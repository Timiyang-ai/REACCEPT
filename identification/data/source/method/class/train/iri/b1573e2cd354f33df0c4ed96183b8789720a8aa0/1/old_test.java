    @Test
    public void parseHeader() {
        ProtocolHeader header = null;
        try {
            ByteBuffer buf = ByteBuffer.allocate(3);
            buf.put(ProtocolMessage.HANDSHAKE.getTypeID());
            buf.putShort((ProtocolMessage.HANDSHAKE.getMaxLength()));
            buf.flip();
            header = Protocol.parseHeader(buf);
        } catch (Exception e) {
            fail("didn't expect any exceptions");
        }
        assertEquals("should be of type handshake message", ProtocolMessage.HANDSHAKE.getTypeID(),
                header.getMessageType().getTypeID());
        assertEquals("length should be of handshake message length", ProtocolMessage.HANDSHAKE.getMaxLength(),
                header.getMessageLength());
    }