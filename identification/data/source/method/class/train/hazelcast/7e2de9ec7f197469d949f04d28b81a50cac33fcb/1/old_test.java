@Test(expected = IllegalArgumentException.class)
    public void test_checkNearCacheConfig_NATIVE() {
        checkNearCacheConfig(getNearCacheConfig(NATIVE), false);
    }