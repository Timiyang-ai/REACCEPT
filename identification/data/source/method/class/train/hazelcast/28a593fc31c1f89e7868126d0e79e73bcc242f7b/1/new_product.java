public static void checkMapConfig(MapConfig mapConfig) {
        checkMergePolicy(mapConfig.isStatisticsEnabled(), mapConfig.getMergePolicyConfig().getPolicy());
        checkNotNative(mapConfig.getInMemoryFormat());

        logIgnoredConfig(mapConfig);
    }