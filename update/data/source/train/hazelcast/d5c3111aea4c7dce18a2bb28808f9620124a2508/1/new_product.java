public static void checkMapConfig(MapConfig mapConfig) {
        checkMergePolicy(mapConfig.isStatisticsEnabled(), mapConfig.getMergePolicyConfig().getPolicy());
        checkNotNative(mapConfig.getInMemoryFormat());
        checkMergePolicySupportsNative(mapConfig.getName(),
                mapConfig.getMergePolicyConfig().getPolicy(), mapConfig.getInMemoryFormat());

        logIgnoredConfig(mapConfig);
    }