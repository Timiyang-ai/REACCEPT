@Test
    public void testDeleteConfig() throws Exception {
        ospfProcess = new OspfProcessImpl();
        ospfArea = new OspfAreaImpl();
        ospfInterface = new OspfInterfaceImpl();
        ospfInterfaces = new ArrayList();
        ospfInterface.setIpAddress(Ip4Address.valueOf("10.10.10.5"));
        ospfInterfaces.add(ospfInterface);
        ospfArea.setAreaId(Ip4Address.valueOf("2.2.2.2"));
        ospfArea.setOspfInterfaceList(ospfInterfaces);
        ospfProcess.setProcessId("10.10.10.10");
        areas = new ArrayList();
        areas.add(ospfArea);
        ospfProcess.setAreas(areas);
        ospfProcesses = new ArrayList();
        ospfProcesses.add(ospfProcess);
        process1 = new OspfProcessImpl();
        process1.setProcessId("11.11.11.11");
        ospfArea1 = new OspfAreaImpl();
        ospfArea1.setAreaId(Ip4Address.valueOf("2.2.2.2"));
        ospfArea1.setOspfInterfaceList(ospfInterfaces);
        areas.add(ospfArea1);
        process1.setAreas(areas);
        ospfProcesses.add(process1);
        ospfController.deleteConfig(ospfProcesses, "INTERFACE");
        assertThat(ospfController, is(notNullValue()));
    }