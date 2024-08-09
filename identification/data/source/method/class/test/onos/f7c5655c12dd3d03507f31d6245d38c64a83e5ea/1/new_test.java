@Test
    public void testNoNeighborInLsaExchangeProcess() throws Exception {
        ospfArea.setOspfInterfaceList(ospfInterfaces);
        ospfArea.noNeighborInLsaExchangeProcess();
        assertThat(ospfArea, is(notNullValue()));
    }