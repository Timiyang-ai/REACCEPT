@Override
    public InMemoryKeyValueService createRawKeyValueService(
            KeyValueServiceConfig config,
            Optional<LeaderConfig> leaderConfig,
            Optional<String> unused,
            boolean initializeAsync) {
        if (initializeAsync) {
            log.warn("Asynchronous initialization not implemented, will initialize synchronousy.");
        }

        AtlasDbVersion.ensureVersionReported();
        return new InMemoryKeyValueService(false);
    }