public CompletableFuture<UpdateStreamStatus.Status> sealStream(String scope, String stream, OperationContext contextOpt) {
        final OperationContext context = contextOpt == null ? streamMetadataStore.createContext(scope, stream) : contextOpt;
        final long requestId = requestTracker.getRequestIdFor("sealStream", scope, stream);

        // 1. post event for seal.
        SealStreamEvent event = new SealStreamEvent(scope, stream, requestId);
        return writeEvent(event)
                // 2. set state to sealing
                .thenCompose(x -> streamMetadataStore.getVersionedState(scope, stream, context, executor))
                .thenCompose(state -> {
                    if (state.getObject().equals(State.SEALED)) {
                        return CompletableFuture.completedFuture(state);
                    } else {
                        return streamMetadataStore.updateVersionedState(scope, stream, State.SEALING, state, context, executor);
                    }
                })
                // 3. return with seal initiated.
                .thenCompose(result -> {
                    if (result.getObject().equals(State.SEALED) || result.getObject().equals(State.SEALING)) {
                        return checkDone(() -> isSealed(scope, stream, context))
                                .thenApply(x -> UpdateStreamStatus.Status.SUCCESS);
                    } else {
                        return CompletableFuture.completedFuture(UpdateStreamStatus.Status.FAILURE);
                    }
                })
                .exceptionally(ex -> {
                    log.warn(requestId, "Exception thrown in trying to notify sealed segments {}", ex.getMessage());
                    return handleUpdateStreamError(ex, requestId);
                });
    }