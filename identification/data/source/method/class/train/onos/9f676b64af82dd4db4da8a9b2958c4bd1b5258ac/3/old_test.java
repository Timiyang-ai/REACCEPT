    @Test
    public void inSameSubnet() {
        assertTrue(devConfig.inSameSubnet(DEV1, PREFIX1.address()));
        assertTrue(devConfig.inSameSubnet(DEV1, PREFIX2.address()));
        assertFalse(devConfig.inSameSubnet(DEV1, ROUTE1.address()));
    }