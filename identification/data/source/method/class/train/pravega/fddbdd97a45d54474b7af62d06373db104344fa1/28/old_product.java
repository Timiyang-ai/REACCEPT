@Task(name = "sealStream", version = "1.0", resource = "{scope}/{stream}")
    public CompletableFuture<UpdateStreamStatus> sealStream(String scope, String stream) {
        return execute(
                new Resource(scope, stream),
                new Serializable[]{scope, stream},
                () -> sealStreamBody(scope, stream));
    }