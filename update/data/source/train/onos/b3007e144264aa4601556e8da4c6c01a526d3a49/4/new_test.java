@Test
    public void testSetSuppressHostByPort() throws Exception {
        ImmutableSet.Builder<ConnectPoint> builder = ImmutableSet.builder();
        builder.add(PORT_3);
        config.setSuppressHostByPort(builder.build());

        Set<ConnectPoint> suppressHostByPort = config.suppressHostByPort();
        assertNotNull("suppressHostByPort should not be null", suppressHostByPort);
        assertThat(suppressHostByPort.size(), is(1));
        assertTrue(suppressHostByPort.contains(PORT_3));
    }