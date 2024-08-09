@Test(expected = IllegalArgumentException.class)
    public void checkMapConfig_NATIVE() {
        checkMapConfig(getMapConfig(NATIVE), nativeMemoryConfig, mapMergePolicyProvider, properties);
    }