public static void checkNearCacheConfig(NearCacheConfig nearCacheConfig, boolean isClient) {
        checkNotNative(nearCacheConfig.getInMemoryFormat());
        checkEvictionConfig(nearCacheConfig.getEvictionConfig(), true);

        if (isClient && nearCacheConfig.isCacheLocalEntries()) {
            throw new IllegalArgumentException(
                    "The Near Cache option `cache-local-entries` is not supported in client configurations!");
        }
    }