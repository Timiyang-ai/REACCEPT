@Test
    public void testElectRouter() throws Exception {
        ospfInterface.setOspfArea(ospfArea);
        ospfInterface.setDr(Ip4Address.valueOf("3.3.3.3"));
        ospfInterface.setBdr(Ip4Address.valueOf("3.3.3.3"));
        ospfInterface.setIpNetworkMask(Ip4Address.valueOf("255.255.255.255"));
        ChannelConfig channelConfig = EasyMock.createMock(ChannelConfig.class);
        EasyMock.expect(channelConfig.getBufferFactory()).andReturn(new HeapChannelBufferFactory());
        Channel channel = EasyMock.createMock(Channel.class);
        ospfInterface.electRouter(channel);
        assertThat(ospfInterface.dr(), is(notNullValue()));
    }