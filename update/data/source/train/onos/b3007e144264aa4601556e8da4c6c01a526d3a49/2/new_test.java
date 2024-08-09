@Test
    public void testSuppressHostByPort() throws Exception {
        Set<ConnectPoint> suppressHostByPort = config.suppressHostByPort();
        assertNotNull("suppressHostByPort should not be null", suppressHostByPort);
        assertThat(suppressHostByPort.size(), is(2));
        assertTrue(suppressHostByPort.contains(PORT_1));
        assertTrue(suppressHostByPort.contains(PORT_2));
    }