@Task(name = "checkStreamExists", version = "1.0", resource = "{scope}/{stream}")
    public CompletableFuture<CreateStreamStatus> createStream(String scope, String stream, StreamConfiguration config, long createTimestamp) {
        return execute(
                new Resource(scope, stream),
                new Serializable[]{scope, stream, config},
                () -> createStreamBody(scope, stream, config, createTimestamp));
    }