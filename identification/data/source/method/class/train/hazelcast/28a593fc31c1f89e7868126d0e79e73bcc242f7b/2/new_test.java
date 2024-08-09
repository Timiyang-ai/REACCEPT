    @Test
    public void checkMapConfig_BINARY() {
        checkMapConfig(getMapConfig(BINARY), nativeMemoryConfig, splitBrainMergePolicyProvider, properties);
    }