@Test
    public void testDeserialize() throws Exception {
        UDP udp = deserializer.deserialize(bytePacketUDP4, 0, bytePacketUDP4.length);

        assertThat(udp.getSourcePort(), is((short) 0x50));
        assertThat(udp.getDestinationPort(), is((short) 0x60));
        assertThat(udp.getLength(), is((short) 8));
        assertThat(udp.getChecksum(), is((short) 0x7bda));
    }