@Test
    public void testReadFromBuffer() throws Exception {
        channelBuffer = ChannelBuffers.copiedBuffer(framePacket(packet1));
        ospfMessageReader.readFromBuffer(channelBuffer);

        channelBuffer = ChannelBuffers.copiedBuffer(framePacket(packet2));
        ospfMessageReader.readFromBuffer(channelBuffer);

        channelBuffer = ChannelBuffers.copiedBuffer(framePacket(packet3));
        ospfMessageReader.readFromBuffer(channelBuffer);

        channelBuffer = ChannelBuffers.copiedBuffer(framePacket(packet4));
        ospfMessageReader.readFromBuffer(channelBuffer);

        channelBuffer = ChannelBuffers.copiedBuffer(framePacket(packet5));
        ospfMessageReader.readFromBuffer(channelBuffer);
        assertThat(ospfMessageReader, is(notNullValue()));
    }