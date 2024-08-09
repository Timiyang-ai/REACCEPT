@Test
    public void testHandleDHCPDiscover() throws Exception {
        DHCPInstance instance = initInstance();
        IPv4Address clientIP = IPv4Address.NONE;

        OFPacketOut dhcpOffer = handler.handleDHCPDiscover(sw, OFPort.of(1), instance, clientIP, dhcpPayload);

        OFActionOutput output = sw.getOFFactory().actions().buildOutput()
                                .setMaxLen(0xffFFffFF)
                                .setPort(OFPort.of(1))
                                .build();

        assertEquals(output, dhcpOffer.getActions().get(0));

    }