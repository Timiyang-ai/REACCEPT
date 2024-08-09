@Test
    public void testSerialize() {
        TCP tcp = new TCP();
        tcp.setSourcePort((short) 0x50);
        tcp.setDestinationPort((short) 0x60);
        tcp.setSequence(0x10);
        tcp.setAcknowledge(0x20);
        tcp.setDataOffset((byte) 0x5);
        tcp.setFlags((short) 0x2);
        tcp.setWindowSize((short) 0x1000);
        tcp.setUrgentPointer((short) 0x1);

        tcp.setParent(ipv4);
        assertArrayEquals(bytePacketTCP4, tcp.serialize());
        tcp.resetChecksum();
        tcp.setParent(ipv6);
        assertArrayEquals(bytePacketTCP6, tcp.serialize());
    }