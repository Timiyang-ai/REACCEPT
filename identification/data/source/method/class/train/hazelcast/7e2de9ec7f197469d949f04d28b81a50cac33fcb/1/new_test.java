@Test(expected = IllegalArgumentException.class)
    public void checkNearCacheConfig_NATIVE() {
        checkNearCacheConfig(getNearCacheConfig(NATIVE), false);
    }