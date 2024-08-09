@Test(expected = InvalidConfigurationException.class)
    public void checkMapConfig_NATIVE() {
        checkMapConfig(getMapConfig(NATIVE), nativeMemoryConfig, splitBrainMergePolicyProvider, properties);
    }