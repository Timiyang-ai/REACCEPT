@Test(expected = Exception.class)
    public void testProcessDdMessage4() throws Exception {
        ospfInterface.setIpAddress(Ip4Address.valueOf("11.11.11.11"));
        ospfInterface.setInterfaceType(2);
        ospfInterface.setIpNetworkMask(Ip4Address.valueOf("255.255.255.255"));
        ospfInterface.setHelloIntervalTime(10);
        ospfInterface.setRouterDeadIntervalTime(10);
        ospfArea.setAreaId(Ip4Address.valueOf("12.12.12.12"));
        channelHandlerContext = EasyMock.createMock(ChannelHandlerContext.class);
        OspfMessage message;
        ddPacket = new DdPacket();
        ddPacket.setSourceIp(Ip4Address.valueOf("1.1.1.1"));
        ddPacket.setOspfVer(2);
        ddPacket.setAreaId(Ip4Address.valueOf("12.12.12.12"));
        ddPacket.setRouterId(Ip4Address.valueOf("2.2.2.2"));
        ddPacket.setIsOpaqueCapable(true);
        ddPacket.setIsMore(1);
        ddPacket.setIsInitialize(1);
        ddPacket.setIsMaster(1);
        ddPacket.setSequenceNo(123);
        message = ddPacket;
        ospfNbrHashMap = new HashMap();
        ospfNbr = new OspfNbrImpl(ospfArea, ospfInterface, Ip4Address.valueOf("10.10.10.10"),
                                  Ip4Address.valueOf("2.2.2.2"), 2,
                                  topologyForDeviceAndLink);
        ospfNbr.setLastDdPacket(createDdPacket());
        ospfNbr.setNeighborId(Ip4Address.valueOf("2.2.2.2"));
        ospfNbr.setState(OspfNeighborState.EXCHANGE);
        ospfNbr.setRouterPriority(0);
        ospfNbr.setNeighborDr(Ip4Address.valueOf("13.13.13.13"));
        ospfNbr.setDdSeqNum(123);
        ospfInterface.addNeighbouringRouter(ospfNbr);
        ospfInterface.setState(OspfInterfaceState.POINT2POINT);
        channelHandlerContext = null;
        channelHandlerContext = EasyMock.createMock(ChannelHandlerContext.class);
        ddPacket.setIsMore(1);
        ddPacket.setIsInitialize(0);
        ddPacket.setIsMaster(1);
        ddPacket.setSequenceNo(123);
        ospfInterface.addNeighbouringRouter(ospfNbr);
        ospfNbr.setState(OspfNeighborState.EXCHANGE);
        ospfInterface.addNeighbouringRouter(ospfNbr);
        channelHandlerContext = null;
        channelHandlerContext = EasyMock.createMock(ChannelHandlerContext.class);
        ospfInterface.processDdMessage(message, channelHandlerContext);
        assertThat(ospfInterfaceChannelHandler, is(notNullValue()));

    }