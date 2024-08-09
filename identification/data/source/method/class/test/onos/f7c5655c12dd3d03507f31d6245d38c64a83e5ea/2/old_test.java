@Test
    public void testSetPollInterval() throws Exception {
        ospfInterface.setPollInterval(100);
        assertThat(ospfInterface.pollInterval(), is(100));
    }