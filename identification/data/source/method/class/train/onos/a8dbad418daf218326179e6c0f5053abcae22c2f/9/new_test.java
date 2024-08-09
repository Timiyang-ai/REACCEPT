    @Test
    public void getSubnets() {
        Set<IpPrefix> expect = Sets.newHashSet(PREFIX1, PREFIX2, ROUTE1);
        assertEquals(expect, devConfig.getSubnets(DEV1));
    }