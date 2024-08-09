    @Test
    public void hasMask() {
        Assert.assertFalse(InetAddressUtils.hasMask(null));
        Assert.assertFalse(InetAddressUtils.hasMask("/"));
        Assert.assertFalse(InetAddressUtils.hasMask("1234/"));
        Assert.assertFalse(InetAddressUtils.hasMask("/1234"));
        Assert.assertFalse(InetAddressUtils.hasMask("1234/1234/1234"));
        Assert.assertFalse(InetAddressUtils.hasMask("1234//1234"));

        Assert.assertTrue(InetAddressUtils.hasMask("1234/1234"));
    }