@Test(expected = Exception.class)
    public void testAddDeviceInformation() throws Exception {
        ospfNbr = new OspfNbrImpl(ospfArea, createOspfInterface(), Ip4Address.valueOf("10.10.10.10"),
                                  Ip4Address.valueOf("10.226.165.100"), 2,
                                  topologyForDeviceAndLink);

        ospfInterface.addDeviceInformation(new OspfRouterImpl());
        assertThat(ospfInterfaceChannelHandler, is(notNullValue()));
    }