@Test
    public void testProcessLSUpdateMessage() throws Exception {
        ospfInterface.setIpAddress(Ip4Address.valueOf("11.11.11.11"));
        ospfInterface.setInterfaceType(2);
        ospfInterface.setIpNetworkMask(Ip4Address.valueOf("255.255.255.255"));
        ospfInterface.setHelloIntervalTime(10);
        ospfInterface.setRouterDeadIntervalTime(10);
        ospfArea.setAreaId(Ip4Address.valueOf("12.12.12.12"));
        channelHandlerContext = EasyMock.createMock(ChannelHandlerContext.class);
        OspfMessage message;
        lsUpdate = new LsUpdate();
        lsUpdate.setSourceIp(Ip4Address.valueOf("1.1.1.1"));
        lsUpdate.setOspfVer(2);
        lsUpdate.setAreaId(Ip4Address.valueOf("12.12.12.12"));
        lsUpdate.setRouterId(Ip4Address.valueOf("10.226.165.100"));
        RouterLsa routerLsa = new RouterLsa();
        lsUpdate.addLsa(routerLsa);
        lsUpdate.setNumberOfLsa(1);
        message = lsUpdate;
        ospfNbrHashMap = new HashMap();
        ospfNbr.setState(OspfNeighborState.FULL);
        ospfNbr = new OspfNbrImpl(ospfArea, createOspfInterface(), Ip4Address.valueOf("10.10.10.10"),
                                  Ip4Address.valueOf("10.226.165.100"), 2,
                                  topologyForDeviceAndLink);
        ospfNbr.setLastDdPacket(createDdPacket());
        ospfNbr.setNeighborId(Ip4Address.valueOf("2.2.2.2"));
        ospfNbr.setState(OspfNeighborState.FULL);
        ospfNbr.setRouterPriority(0);
        ospfNbr.setNeighborDr(Ip4Address.valueOf("13.13.13.13"));
        ospfNbr.setDdSeqNum(123);
        ospfInterface.addNeighbouringRouter(ospfNbr);
        channelHandlerContext = null;
        channelHandlerContext = EasyMock.createMock(ChannelHandlerContext.class);
        ospfInterface.processLsUpdateMessage(message, channelHandlerContext);
        assertThat(ospfInterfaceChannelHandler, is(notNullValue()));

    }