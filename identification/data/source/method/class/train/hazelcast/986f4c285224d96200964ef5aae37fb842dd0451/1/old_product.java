public static void checkNearCacheConfig(String mapName, NearCacheConfig nearCacheConfig, boolean isClient) {
        checkLocalUpdatePolicy(mapName, nearCacheConfig);
        checkNotNative(nearCacheConfig.getInMemoryFormat());
        checkEvictionConfig(nearCacheConfig.getEvictionConfig(), true);

        if (isClient && nearCacheConfig.isCacheLocalEntries()) {
            LOGGER.warning("The Near Cache option `cache-local-entries` is not supported in client configurations. "
                    + "Remove this option from your client configuration, future versions may fail startup with an exception.");
        }
        checkPreloaderConfig(nearCacheConfig, isClient);
    }