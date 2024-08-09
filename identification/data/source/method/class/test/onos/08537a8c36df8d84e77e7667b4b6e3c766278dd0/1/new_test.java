@Test
    public void testBuildNdpSolicit() throws Exception {
        final Ethernet ethPacket = NeighborSolicitation.buildNdpSolicit(TARGET_IP,
                SRC_IP, DST_IP, SRC_MAC, DST_MAC, VLAN_ID);

        assertTrue(ethPacket.getDestinationMAC().equals(DST_MAC));
        assertTrue(ethPacket.getSourceMAC().equals(SRC_MAC));
        assertTrue(ethPacket.getEtherType() == Ethernet.TYPE_IPV6);
        assertTrue(ethPacket.getVlanID() == VLAN_ID.id());

        final IPv6 ipPacket = (IPv6) ethPacket.getPayload();

        assertArrayEquals(ipPacket.getSourceAddress(), SRC_IP.toOctets());
        assertArrayEquals(ipPacket.getDestinationAddress(), DST_IP.toOctets());

        final ICMP6 icmp6Packet = (ICMP6) ipPacket.getPayload();
        final NeighborSolicitation nsPacket = (NeighborSolicitation) icmp6Packet.getPayload();

        assertArrayEquals(nsPacket.getTargetAddress(), TARGET_IP.toOctets());

        assertEquals("Non-DAD NS should have 1 option", 1, nsPacket.getOptions().size());
        assertEquals("The option should be SRC_LL_ADDR type", TYPE_SOURCE_LL_ADDRESS,
                nsPacket.getOptions().stream().findFirst().get().type());
    }