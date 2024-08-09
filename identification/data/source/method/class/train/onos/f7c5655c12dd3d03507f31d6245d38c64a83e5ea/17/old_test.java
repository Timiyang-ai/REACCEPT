@Test
    public void testSetTransmitDelay() throws Exception {
        ospfInterface.setTransmitDelay(100);
        assertThat(ospfInterface.transmitDelay(), is(100));
    }