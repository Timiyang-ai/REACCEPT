@Test
    public void testAdjOk() throws Exception {
        channel = EasyMock.createMock(Channel.class);
        ospfInterface.setInterfaceType(OspfInterfaceType.BROADCAST.value());
        ospfInterface.setIpAddress(Ip4Address.valueOf("2.2.2.2"));
        ospfNbr1 = new OspfNbrImpl(ospfArea, ospfInterface, Ip4Address.valueOf("1.1.1.1"),
                                   Ip4Address.valueOf("2.2.2.2"), 2,
                                   topologyForDeviceAndLink);
        ospfNbr1.setState(OspfNeighborState.TWOWAY);
        ospfNbr1.setNeighborDr(Ip4Address.valueOf("2.2.2.2"));
        ospfNbr1.adjOk(channel);
        assertThat(ospfNbr1, is(notNullValue()));

        ospfInterface.setInterfaceType(OspfInterfaceType.POINT_TO_POINT.value());
        ospfNbr1 = new OspfNbrImpl(ospfArea, ospfInterface, Ip4Address.valueOf("1.1.1.1"),
                                   Ip4Address.valueOf("2.2.2.2"), 2,
                                   topologyForDeviceAndLink);
        channel = null;
        channel = EasyMock.createMock(Channel.class);
        ospfNbr1.adjOk(channel);
        assertThat(ospfNbr1, is(notNullValue()));
    }