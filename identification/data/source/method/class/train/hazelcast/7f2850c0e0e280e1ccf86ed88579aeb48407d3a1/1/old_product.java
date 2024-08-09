public static void checkNearCacheConfig(String mapName, NearCacheConfig nearCacheConfig, boolean isClient) {
        checkLocalUpdatePolicy(mapName, nearCacheConfig);
        checkNotNative(nearCacheConfig.getInMemoryFormat());
        checkEvictionConfig(nearCacheConfig.getEvictionConfig(), true);

        if (isClient && nearCacheConfig.isCacheLocalEntries()) {
            throw new IllegalArgumentException("The Near Cache option `cache-local-entries` is not supported in "
                    + "client configurations.");
        }
        checkPreloaderConfig(nearCacheConfig, isClient);
    }