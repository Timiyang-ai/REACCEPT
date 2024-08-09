@Task(name = "deleteStream", version = "1.0", resource = "{scope}/{stream}")
    public CompletableFuture<DeleteStreamStatus.Status> deleteStream(final String scope, final String stream,
                                                                     final OperationContext contextOpt) {
        return execute(
                new Resource(scope, stream),
                new Serializable[]{scope, stream, null},
                () -> deleteStreamBody(scope, stream, contextOpt));
    }