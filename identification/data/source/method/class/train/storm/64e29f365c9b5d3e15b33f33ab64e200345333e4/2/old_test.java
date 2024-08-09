    @Test
    public void isZkAuthenticationConfiguredTopologyTest() {
        Assert.assertFalse(
            "Returns null if given null config",
            Utils.isZkAuthenticationConfiguredTopology(null));

        Assert.assertFalse(
            "Returns false if scheme key is missing",
            Utils.isZkAuthenticationConfiguredTopology(emptyMockMap()));

        Assert.assertFalse(
            "Returns false if scheme value is null",
            Utils.isZkAuthenticationConfiguredTopology(topologyMockMap(null)));

        Assert.assertTrue(
            "Returns true if scheme value is string",
            Utils.isZkAuthenticationConfiguredTopology(topologyMockMap("foobar")));
    }