@Test
    public void testBadLSReq() throws Exception {
        channel = EasyMock.createMock(Channel.class);
        ospfNbr.setState(OspfNeighborState.EXCHANGE);
        ospfNbr.badLSReq(channel);
        assertThat(ospfNbr, is(notNullValue()));
    }