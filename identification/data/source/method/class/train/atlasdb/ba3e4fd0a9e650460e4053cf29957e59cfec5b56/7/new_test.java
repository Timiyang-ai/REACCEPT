    private KeyValueService createRawKeyValueService(boolean initializeAsync) {
        return factory.createRawKeyValueService(
                null,
                null,
                Optional::empty,
                null,
                Optional.empty(),
                null,
                initializeAsync);
    }