@Nonnull
    public CompletableFuture<Void> updateMapConfig(Member member,
                                                   UpdateMapConfigParameters parameters) {
        checkNotNull(member);
        checkNotNull(parameters);
        checkNotNull(parameters.getEvictionPolicy());
        checkNotNull(parameters.getMaxSizePolicy());

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
                parameters.getMap(),
                member.getAddress()
        );
        return new ClientDelegatingFuture<>(
                invocation.invoke(),
                serializationService,
                clientMessage -> null
        );
    }