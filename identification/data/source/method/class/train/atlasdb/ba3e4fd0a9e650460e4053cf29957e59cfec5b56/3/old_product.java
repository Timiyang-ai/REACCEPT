@Override
    public InMemoryKeyValueService createRawKeyValueService(
            KeyValueServiceConfig config,
            Optional<LeaderConfig> leaderConfig,
            Optional<String> unused) {
        AtlasDbVersion.ensureVersionReported();
        return new InMemoryKeyValueService(false);
    }