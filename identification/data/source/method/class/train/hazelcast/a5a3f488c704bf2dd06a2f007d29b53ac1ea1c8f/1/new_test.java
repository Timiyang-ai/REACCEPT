@Test(expected = InvalidConfigurationException.class)
    public void checkNearCacheConfig_NATIVE() {
        checkNearCacheConfig(MAP_NAME, getNearCacheConfig(NATIVE), null, false);
    }