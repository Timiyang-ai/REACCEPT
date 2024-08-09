@Test
    public void testNoNeighborInLsaExchangeProcess() throws Exception {
        ospfInterfaces = new ArrayList();
        ospfInterface1 = new OspfInterfaceImpl();
        ospfInterface1.setIpAddress(Ip4Address.valueOf("1.1.1.1"));
        ospfNbr = new OspfNbrImpl(new OspfAreaImpl(), new OspfInterfaceImpl(),
                                  Ip4Address.valueOf("1.1.1.1"),
                                  Ip4Address.valueOf("2.2.2.2"), 2,
                                  new OspfInterfaceChannelHandler(new Controller(),
                                                                  new OspfAreaImpl(),
                                                                  new OspfInterfaceImpl()),
                                  topologyForDeviceAndLink);
        ospfNbr.setState(OspfNeighborState.EXCHANGE.EXCHANGE);
        ospfInterface1.addNeighbouringRouter(ospfNbr);
        ospfInterfaces.add(ospfInterface1);
        ospfArea.setInterfacesLst(ospfInterfaces);
        assertThat(ospfArea.noNeighborInLsaExchangeProcess(), is(false));
    }