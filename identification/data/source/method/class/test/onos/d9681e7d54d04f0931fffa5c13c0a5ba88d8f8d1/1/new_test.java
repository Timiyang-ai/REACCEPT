@Test
    public void testSetSuppressHost() throws Exception {
        ImmutableSet.Builder<ConnectPoint> builder = ImmutableSet.builder();
        builder.add(PORT_3);
        config.setSuppressHost(builder.build());

        Set<ConnectPoint> suppressHost = config.suppressHost();
        assertNotNull("suppressHost should not be null", suppressHost);
        assertThat(suppressHost.size(), is(1));
        assertTrue(suppressHost.contains(PORT_3));
    }