@Test
    public void testUpdateConfig1() throws Exception {
        ospfProcess = new OspfProcessImpl();
        ospfArea = new OspfAreaImpl();
        ospfInterface = new OspfInterfaceImpl();
        ospfInterfaces = new ArrayList();
        ospfInterface.setIpAddress(Ip4Address.valueOf("10.10.10.5"));
        ospfInterfaces.add(ospfInterface);
        ospfArea.setAreaId(Ip4Address.valueOf("2.2.2.2"));
        ospfArea.setOspfInterfaceList(ospfInterfaces);
        ospfProcess.setProcessId("10.10.10.10");
        ospfAreas = new ArrayList();
        ospfAreas.add(ospfArea);
        ospfProcess.setAreas(ospfAreas);
        ospfProcesses = new ArrayList();
        ospfProcesses.add(ospfProcess);
        controller.updateConfig(ospfProcesses);
        assertThat(controller, is(notNullValue()));
    }