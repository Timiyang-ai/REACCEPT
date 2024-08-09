public static void checkMapConfig(MapConfig mapConfig, MergePolicyProvider mergePolicyProvider) {
        checkNotNativeWhenOpenSource(mapConfig.getInMemoryFormat());
        checkMapMergePolicy(mapConfig, mergePolicyProvider);
        logIgnoredConfig(mapConfig);
    }