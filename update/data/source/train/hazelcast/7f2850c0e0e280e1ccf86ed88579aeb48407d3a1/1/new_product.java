public static void checkNearCacheConfig(String mapName, NearCacheConfig nearCacheConfig,
                                            NativeMemoryConfig nativeMemoryConfig, boolean isClient) {
        InMemoryFormat inMemoryFormat = nearCacheConfig.getInMemoryFormat();

        checkNotNative(inMemoryFormat);
        checkLocalUpdatePolicy(mapName, nearCacheConfig.getLocalUpdatePolicy());
        checkEvictionConfig(nearCacheConfig.getEvictionConfig(), true);
        checkOnHeapNearCacheMaxSizePolicy(nearCacheConfig);
        checkNearCacheNativeMemoryConfig(nearCacheConfig.getInMemoryFormat(), nativeMemoryConfig, getBuildInfo().isEnterprise());

        if (isClient && nearCacheConfig.isCacheLocalEntries()) {
            throw new IllegalArgumentException("The Near Cache option `cache-local-entries` is not supported in "
                    + "client configurations.");
        }
        checkPreloaderConfig(nearCacheConfig, isClient);
    }