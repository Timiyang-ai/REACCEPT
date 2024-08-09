@Test
    public void testExcludePorts() throws Exception {
        Set<String> excludePorts = config.excludePorts();
        assertThat(excludePorts.size(), is(2));
        assertTrue(excludePorts.contains(PORT_NAME_1));
        assertTrue(excludePorts.contains(PORT_NAME_2));
    }