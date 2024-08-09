@Task(name = "updateConfig", version = "1.0", resource = "{scope}/{stream}")
    public CompletableFuture<UpdateStreamStatus.Status> updateStream(String scope, String stream, StreamConfiguration config, OperationContext contextOpt) {
        return execute(
                new Resource(scope, stream),
                new Serializable[]{scope, stream, config, null},
                () -> updateStreamConfigBody(scope, stream, config, contextOpt));
    }