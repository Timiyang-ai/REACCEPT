public static void checkMapConfig(MapConfig mapConfig,
                                      NativeMemoryConfig nativeMemoryConfig,
                                      SplitBrainMergePolicyProvider mergePolicyProvider,
                                      HazelcastProperties properties) {

        checkNotNativeWhenOpenSource(mapConfig.getInMemoryFormat());

        if (getBuildInfo().isEnterprise()) {
            checkMapNativeConfig(mapConfig, nativeMemoryConfig);
            checkHotRestartSpecificConfig(mapConfig, properties);
        }

        checkMapEvictionConfig(mapConfig.getEvictionConfig());
        checkMapMergePolicy(mapConfig, mergePolicyProvider);
    }