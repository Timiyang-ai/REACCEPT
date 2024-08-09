@Test
    public void testSetSubnetAddres() throws Exception {
        reachability.setSubnetAddres(ip4Address);
        result = reachability.getSubnetAddres();
        assertThat(result, is(ip4Address));
    }