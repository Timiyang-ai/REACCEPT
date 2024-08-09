public static void checkMapConfig(MapConfig mapConfig, NativeMemoryConfig nativeMemoryConfig,
                                      SplitBrainMergePolicyProvider mergePolicyProvider,
                                      HazelcastProperties properties) {
        checkNotNativeWhenOpenSource(mapConfig.getInMemoryFormat());

        boolean enterprise = getBuildInfo().isEnterprise();
        if (enterprise) {
            checkNativeConfig(mapConfig, nativeMemoryConfig);
            checkHotRestartSpecificConfig(mapConfig, properties);
        }
        checkMapMergePolicy(mapConfig, mergePolicyProvider);
    }