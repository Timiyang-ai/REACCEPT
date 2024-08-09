@Test
    public void testReadFrom() {
        tlvHeader = new TlvHeader();
        tlvHeader.setTlvType(3);
        tlvHeader.setTlvLength(4);
        interfaceIpAddress = new NeighborIpAddress(tlvHeader);
        channelBuffer = ChannelBuffers.copiedBuffer(packet);
        interfaceIpAddress.readFrom(channelBuffer);
        assertThat(interfaceIpAddress, is(notNullValue()));
    }