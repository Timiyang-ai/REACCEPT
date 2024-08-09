@Test(expected = IllegalArgumentException.class)
    @Ignore("As of 3.8 a warning is logged when client configuration specifies cache-local-entries option. "
            + "This test is to be enabled again in 3.9, when this configuration will throw an exception.")
    public void checkNearCacheConfig_withUnsupportedClientConfig() {
        checkNearCacheConfig(MAP_NAME, getNearCacheConfig(BINARY), true);
    }