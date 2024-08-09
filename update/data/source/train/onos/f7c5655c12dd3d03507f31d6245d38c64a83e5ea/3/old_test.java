@Test(expected = Exception.class)
    public void testNegotiationDone() throws Exception {
        ospfLsaList = new ArrayList();
        ospfLsaList.add(new RouterLsa());
        ospfMessage = new HelloPacket();
        ospfNbr.setState(OspfNeighborState.EXSTART);
        channel = EasyMock.createMock(Channel.class);
        ospfNbr.negotiationDone(ospfMessage, true, ospfLsaList, channel);
        channel1 = EasyMock.createMock(Channel.class);
        ospfNbr.negotiationDone(ospfMessage, false, ospfLsaList, channel1);
        assertThat(ospfNbr, is(notNullValue()));
    }