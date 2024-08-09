@Test
    public void testAddToOtherNeighborLsaTxList() throws Exception {
        ospfArea.setOspfInterfaceList(ospfInterfaces);
        ospfArea.addToOtherNeighborLsaTxList(routerLsa);
        assertThat(ospfArea, is(notNullValue()));

        opaqueLsa10.setLsType(OspfParameters.LINK_LOCAL_OPAQUE_LSA);
        ospfArea.addToOtherNeighborLsaTxList(opaqueLsa10);
        assertThat(ospfArea, is(notNullValue()));
    }