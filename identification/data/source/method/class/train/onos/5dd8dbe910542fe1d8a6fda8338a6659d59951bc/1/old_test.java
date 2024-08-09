    @Test
    public void mpUnReachNlriTest() throws BgpParseException {

        // BGP flow spec  Message
        byte[] flowSpecMsg = new byte[] {(byte) 0xff, (byte) 0xff, (byte) 0xff,
                (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
                (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
                (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
                (byte) 0xff, 0x00, 0x2b, 0x02, 0x00, 0x00, 0x00, 0x14,
                (byte) 0x90, 0x0f, 0x00, 0x10, 0x00, 0x01, (byte) 0x85,
                0x0c, 0x02, 0x20, (byte) 0xc0, (byte) 0xa8, 0x07, 0x36,
                0x03, (byte) 0x81, 0x67, 0x04, (byte) 0x81, 0x01};

        byte[] testFsMsg;
        ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
        buffer.writeBytes(flowSpecMsg);

        BgpMessageReader<BgpMessage> reader = BgpFactories.getGenericReader();
        BgpMessage message;
        BgpHeader bgpHeader = new BgpHeader();

        message = reader.readFrom(buffer, bgpHeader);

        assertThat(message, instanceOf(BgpUpdateMsgVer4.class));
        ChannelBuffer buf = ChannelBuffers.dynamicBuffer();
        message.writeTo(buf);

        int readLen = buf.writerIndex();
        testFsMsg = new byte[readLen];
        buf.readBytes(testFsMsg, 0, readLen);

        assertThat(testFsMsg, is(flowSpecMsg));
    }