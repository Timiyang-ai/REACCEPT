@Test
    public void testGetSubnetAddres() throws Exception {
        reachability.setSubnetAddress(ip4Address);
        result = reachability.getSubnetAddress();
        assertThat(result, is(ip4Address));
    }