@Nonnull
    public CompletableFuture<Void> changeClusterState(ClusterState newState) {
        ClientInvocation invocation = new ClientInvocation(
                client,
                MCChangeClusterStateCodec.encodeRequest(newState.getId()),
                null
        );
        return new ClientDelegatingFuture<>(
                invocation.invoke(),
                serializationService,
                clientMessage -> null
        );
    }