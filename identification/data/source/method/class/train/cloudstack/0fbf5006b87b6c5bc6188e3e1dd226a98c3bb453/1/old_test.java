    @Test
    public void internalCanHandleTestAllStoragePoolsAreManaged() {
        configureAndTestInternalCanHandle(true, true, StrategyPriority.HIGHEST);
    }