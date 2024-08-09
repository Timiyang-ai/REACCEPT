@Test(expected = IllegalArgumentException.class)
    public void checkNearCacheConfig_withUnsupportedClientConfig() {
        checkNearCacheConfig(MAP_NAME, getNearCacheConfig(BINARY), true);
    }