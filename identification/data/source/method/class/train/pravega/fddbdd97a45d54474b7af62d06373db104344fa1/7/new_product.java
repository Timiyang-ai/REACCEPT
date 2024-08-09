@Task(name = "sealStream", version = "1.0", resource = "{scope}/{stream}")
    public CompletableFuture<UpdateStreamStatus.Status> sealStream(String scope, String stream, OperationContext contextOpt) {
        return execute(
                new Resource(scope, stream),
                new Serializable[]{scope, stream, null},
                () -> sealStreamBody(scope, stream, contextOpt));
    }