@Override
    public KeyValueService createRawKeyValueService(
            MetricsManager unusedMetricsManager,
            KeyValueServiceConfig unusedConfig,
            Supplier<Optional<KeyValueServiceRuntimeConfig>> unusedRuntimeConfig,
            Optional<LeaderConfig> unusedLeaderConfig,
            Optional<String> unused,
            LongSupplier unusedLongSupplier,
            boolean initializeAsync,
            QosClient unusedQosClient) {
        if (initializeAsync) {
            log.warn("Asynchronous initialization not implemented, will initialize synchronously.");
        }
        AtlasDbVersion.ensureVersionReported();
        return new InMemoryKeyValueService(false);
    }