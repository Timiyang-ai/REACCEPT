@Test
    public void testGetSubnetIps() {
        String bClassCidr = "10.10.0.0/16";
        Set<IpAddress> bClassIps = getSubnetIps(bClassCidr);
        assertEquals(((Double) Math.pow(2, 16)).intValue() - 2, bClassIps.size());

        String cClassCidr = "10.10.10.0/24";
        Set<IpAddress> cClassIps = getSubnetIps(cClassCidr);
        assertEquals(((Double) Math.pow(2, 8)).intValue() - 2, cClassIps.size());

        String dClassCidr = "10.10.10.10/32";
        Set<IpAddress> dClassIps = getSubnetIps(dClassCidr);
        assertEquals(1, dClassIps.size());
    }