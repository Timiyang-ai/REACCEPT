@Test(expected = Exception.class)
    public void testUpdateInterfaceMap() throws Exception {
        IsisInterface isisInterface = new DefaultIsisInterface();
        IsisInterface isisInterface1 = new DefaultIsisInterface();
        isisInterface.setInterfaceIpAddress(ip4Address);
        isisInterface.setInterfaceIndex(1);
        isisInterfaceList.add(isisInterface);
        IsisProcess isisProcess = new DefaultIsisProcess();
        isisProcess.setIsisInterfaceList(isisInterfaceList);
        isisProcessList.add(isisProcess);
        isisChannelHandler.updateInterfaceMap(isisProcessList);
        assertThat(isisChannelHandler, is(notNullValue()));
        isisProcessList = new ArrayList<>();
        isisInterface1.setInterfaceIpAddress(ip4Address);
        isisInterface1.setInterfaceIndex(1);
        isisInterface1.setInterfaceIpAddress(ip4Address);
        isisInterface1.setInterfaceIndex(1);
        isisInterface1.setSystemId("9999.9999.9999");
        isisInterface1.setIntermediateSystemName("router");
        isisInterface1.setReservedPacketCircuitType(3);
        isisInterface1.setCircuitId("10");
        isisInterface1.setNetworkType(IsisNetworkType.BROADCAST);
        isisInterface1.setAreaAddress("490001");
        isisInterface1.setHoldingTime(50);
        isisInterface1.setHelloInterval(10);
        isisInterfaceList.add(isisInterface1);
        isisProcess.setIsisInterfaceList(isisInterfaceList);
        isisProcessList.add(isisProcess);
        isisChannelHandler.updateInterfaceMap(isisProcessList);
        assertThat(isisChannelHandler, is(notNullValue()));
    }