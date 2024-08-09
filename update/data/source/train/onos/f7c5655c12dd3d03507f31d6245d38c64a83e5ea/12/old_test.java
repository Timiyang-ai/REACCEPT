@Test
    public void testReadFromBuffer() throws Exception {
        channelBuffer = ChannelBuffers.copiedBuffer(packet1);
        ospfMessageReader.readFromBuffer(channelBuffer);

        channelBuffer = ChannelBuffers.copiedBuffer(packet2);
        ospfMessageReader.readFromBuffer(channelBuffer);

        channelBuffer = ChannelBuffers.copiedBuffer(packet3);
        ospfMessageReader.readFromBuffer(channelBuffer);

        channelBuffer = ChannelBuffers.copiedBuffer(packet4);
        ospfMessageReader.readFromBuffer(channelBuffer);

        channelBuffer = ChannelBuffers.copiedBuffer(packet5);
        ospfMessageReader.readFromBuffer(channelBuffer);
        assertThat(ospfMessageReader, is(notNullValue()));

    }