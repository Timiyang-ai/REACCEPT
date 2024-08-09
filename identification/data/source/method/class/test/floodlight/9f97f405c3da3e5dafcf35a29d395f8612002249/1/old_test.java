@Test
    public void testAssignLeaseToClientWithRequestIP() throws Exception {
        dhcpPool = initPool(IPv4Address.of("10.0.0.1"), 1);

        // Will return the request IP if it is available
        Optional<IPv4Address> lease = dhcpPool.assignLeaseToClientWithRequestIP(IPv4Address.of("10.0.0.1"), MacAddress.of(1), 60);
        assertTrue(lease.isPresent());
        assertEquals(IPv4Address.of("10.0.0.1"), lease.get());
    }