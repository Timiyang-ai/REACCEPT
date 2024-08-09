@Test
    public void testSetInterfaceCost() throws Exception {
        ospfInterface.setInterfaceCost(100);
        assertThat(ospfInterface.interfaceCost(), is(100));
    }