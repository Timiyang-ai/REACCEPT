@Test
    public void testSuppressHost() throws Exception {
        Set<ConnectPoint> suppressHost = config.suppressHost();
        assertNotNull("suppressHost should not be null", suppressHost);
        assertThat(suppressHost.size(), is(2));
        assertTrue(suppressHost.contains(PORT_1));
        assertTrue(suppressHost.contains(PORT_2));
    }