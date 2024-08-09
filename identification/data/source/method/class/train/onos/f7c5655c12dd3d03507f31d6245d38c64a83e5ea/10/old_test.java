@Test
    public void testGetInterfacesLst() throws Exception {
        ospfInterfaces = new ArrayList();
        ospfInterface1 = new OspfInterfaceImpl();
        ospfInterface1.setIpAddress(Ip4Address.valueOf("1.1.1.1"));
        ospfInterfaces.add(ospfInterface1);
        ospfInterface2 = new OspfInterfaceImpl();
        ospfInterface2.setIpAddress(Ip4Address.valueOf("2.2.2.2"));
        ospfInterfaces.add(ospfInterface2);
        ospfInterface3 = new OspfInterfaceImpl();
        ospfInterface3.setIpAddress(Ip4Address.valueOf("3.3.3.3"));
        ospfInterfaces.add(ospfInterface3);
        ospfArea.setInterfacesLst(ospfInterfaces);
        assertThat(ospfInterfaces.size(), is(3));
        assertThat(ospfArea.getInterfacesLst(), is(notNullValue()));
    }