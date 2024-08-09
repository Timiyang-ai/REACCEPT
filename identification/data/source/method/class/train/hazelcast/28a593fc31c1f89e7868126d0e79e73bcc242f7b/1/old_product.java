public static void checkMapConfig(MapConfig mapConfig) {
        checkNotNative(mapConfig.getInMemoryFormat());

        logIgnoredConfig(mapConfig);
    }