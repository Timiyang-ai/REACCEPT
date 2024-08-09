@Nonnull
    public CompletableFuture<MCMapConfig> getMapConfig(String map) {
        ClientInvocation invocation = new ClientInvocation(
                client,
                MCGetMapConfigCodec.encodeRequest(map),
                map
        );
        return new ClientDelegatingFuture<>(
                invocation.invoke(),
                serializationService,
                clientMessage -> {
                    MCGetMapConfigCodec.ResponseParameters response =
                            MCGetMapConfigCodec.decodeResponse(clientMessage);
                    return MCMapConfig.fromResponse(response);
                },
                true
        );
    }