@Test
    public void testSetAreaId() throws Exception {
        ospfInterface.setAreaId(1);
        assertThat(ospfInterface.areaId(), is(1));
    }