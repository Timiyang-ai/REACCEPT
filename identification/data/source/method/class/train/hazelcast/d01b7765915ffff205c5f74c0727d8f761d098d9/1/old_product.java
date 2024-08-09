@Nonnull
    public CompletableFuture<Boolean> matchMCConfig(Member member, String eTag) {
        checkNotNull(member);
        checkNotNull(eTag);

        ClientInvocation invocation = new ClientInvocation(
                client,
                MCMatchMCConfigCodec.encodeRequest(eTag),
                null,
                member.getAddress()
        );
        return new ClientDelegatingFuture<>(
                invocation.invoke(),
                serializationService,
                clientMessage -> {
                    MCMatchMCConfigCodec.ResponseParameters response =
                            MCMatchMCConfigCodec.decodeResponse(clientMessage);
                    return response.response;
                },
                false
        );
    }