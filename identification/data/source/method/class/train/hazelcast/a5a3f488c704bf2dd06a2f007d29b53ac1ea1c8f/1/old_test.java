@Test(expected = IllegalArgumentException.class)
    public void checkNearCacheConfig_NATIVE() {
        checkNearCacheConfig(MAP_NAME, getNearCacheConfig(NATIVE), null, false);
    }