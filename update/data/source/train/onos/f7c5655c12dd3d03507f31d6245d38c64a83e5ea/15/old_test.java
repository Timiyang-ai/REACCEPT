@Test
    public void testAdjOk() throws Exception {
        channel = EasyMock.createMock(Channel.class);
        ospfInterface.setIpAddress(Ip4Address.valueOf("2.2.2.2"));
        ospfNbr.setState(OspfNeighborState.TWOWAY);
        ospfNbr.setNeighborDr(Ip4Address.valueOf("2.2.2.2"));
        ospfNbr.adjOk(channel);
        Assert.assertNotNull(ospfNbr);
    }