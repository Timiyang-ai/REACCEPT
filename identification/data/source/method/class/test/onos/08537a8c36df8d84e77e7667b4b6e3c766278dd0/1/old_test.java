@Test
    public void testBuildNdpSolicit() throws Exception {
        Ethernet ethPacket = NeighborSolicitation.buildNdpSolicit(TARGET_IP.toOctets(),
                SRC_IP.toOctets(), DST_IP.toOctets(),
                SRC_MAC.toBytes(), DST_MAC.toBytes(), VLAN_ID);
        IPv6 ipPacket = (IPv6) ethPacket.getPayload();
        ICMP6 icmp6Packet = (ICMP6) ipPacket.getPayload();
        NeighborSolicitation nsPacket = (NeighborSolicitation) icmp6Packet.getPayload();

        assertEquals("Non-DAD NS should have 1 option", 1, nsPacket.getOptions().size());
        assertEquals("The option should be SRC_LL_ADDR type", TYPE_SOURCE_LL_ADDRESS,
                nsPacket.getOptions().stream().findFirst().get().type());
    }