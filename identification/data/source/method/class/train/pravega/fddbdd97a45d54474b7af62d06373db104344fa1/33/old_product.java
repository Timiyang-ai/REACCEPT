public CompletableFuture<UpdateStreamStatus.Status> sealStream(String scope, String stream, OperationContext contextOpt) {
        final OperationContext context = contextOpt == null ? streamMetadataStore.createContext(scope, stream) : contextOpt;

        // 1. post event for seal.
        SealStreamEvent event = new SealStreamEvent(scope, stream);
        return writeEvent(event)
                // 2. set state to sealing
                .thenCompose(x -> streamMetadataStore.getState(scope, stream, false, context, executor))
                .thenCompose(state -> {
                    if (state.equals(State.SEALED)) {
                        return CompletableFuture.completedFuture(true);
                    } else {
                        return streamMetadataStore.setState(scope, stream, State.SEALING, context, executor);
                    }
                })
                // 3. return with seal initiated.
                .thenCompose(result -> {
                    if (result) {
                        return checkSealed(scope, stream, context)
                                .thenApply(x -> UpdateStreamStatus.Status.SUCCESS);
                    } else {
                        return CompletableFuture.completedFuture(UpdateStreamStatus.Status.FAILURE);
                    }
                })
                .exceptionally(ex -> {
                    log.warn("Exception thrown in trying to notify sealed segments {}", ex.getMessage());
                    return handleUpdateStreamError(ex);
                });
    }