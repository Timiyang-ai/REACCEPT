    @Test
    public void getPortSubnets() {
        assertEquals(Sets.newHashSet(PREFIX1), devConfig.getPortSubnets(DEV1, PORT1));
        assertEquals(Sets.newHashSet(PREFIX2), devConfig.getPortSubnets(DEV1, PORT2));
    }