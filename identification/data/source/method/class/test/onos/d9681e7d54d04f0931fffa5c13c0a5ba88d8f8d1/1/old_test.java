@Test
    public void testSetExcludePorts() throws Exception {
        ImmutableSet.Builder<String> builder = ImmutableSet.builder();
        builder.add(PORT_NAME_3);
        config.setExcludePorts(builder.build());

        Set<String> excludePorts = config.excludePorts();
        assertThat(excludePorts.size(), is(1));
        assertTrue(excludePorts.contains(PORT_NAME_3));
    }