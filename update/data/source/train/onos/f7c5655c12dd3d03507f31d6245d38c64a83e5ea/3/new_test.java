@Test
    public void testNegotiationDone() throws Exception {

        ospfLsaList = new ArrayList();
        RouterLsa routerLsa = new RouterLsa();
        routerLsa.setLsType(OspfLsaType.ROUTER.value());
        ospfLsaList.add(routerLsa);
        DdPacket ddPacket = new DdPacket();
        ddPacket.setIsOpaqueCapable(true);
        ospfMessage = ddPacket;
        ospfNbr.setState(OspfNeighborState.EXSTART);
        ospfNbr.setIsOpaqueCapable(true);
        channel = null;
        channel = EasyMock.createMock(Channel.class);
        ospfNbr.negotiationDone(ospfMessage, true, ospfLsaList, channel);
        channel1 = EasyMock.createMock(Channel.class);
        ospfNbr.negotiationDone(ospfMessage, false, ospfLsaList, channel1);
        assertThat(ospfNbr, is(notNullValue()));
    }