@Nonnull
    public CompletableFuture<Void> updateMapConfig(UpdateMapConfigParameters parameters) {
        ClientInvocation invocation = new ClientInvocation(
                client,
                MCUpdateMapConfigCodec.encodeRequest(
                        parameters.getMap(),
                        parameters.getTimeToLiveSeconds(),
                        parameters.getMaxIdleSeconds(),
                        parameters.getEvictionPolicy().getId(),
                        parameters.isReadBackupData(),
                        parameters.getMaxSize(),
                        parameters.getMaxSizePolicy().getId()),
                null
        );
        return new ClientDelegatingFuture<>(
                invocation.invoke(),
                serializationService,
                clientMessage -> null
        );
    }