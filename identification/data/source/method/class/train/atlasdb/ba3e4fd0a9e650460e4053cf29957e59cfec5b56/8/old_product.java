@Override
    public InMemoryKeyValueService createRawKeyValueService(
            KeyValueServiceConfig config,
            Supplier<Optional<KeyValueServiceRuntimeConfig>> runtimeConfig,
            Optional<LeaderConfig> leaderConfig,
            Optional<String> unused,
            boolean initializeAsync,
            QosClient unusedQosClient) {
        if (initializeAsync) {
            log.warn("Asynchronous initialization not implemented, will initialize synchronousy.");
        }

        AtlasDbVersion.ensureVersionReported();
        return new InMemoryKeyValueService(false);
    }