@Test
    public void testSolicitationNodeAddress() {
        assertTrue(Arrays.equals(SOLICITATION_NODE_ADDRESS, IPv6.solicitationNodeAddress(DESTINATION_ADDRESS)));
    }