public CompletableFuture<UpdateStreamStatus.Status> updateStream(String scope, String stream, StreamConfiguration config,
                                                                     OperationContext contextOpt) {
        final OperationContext context = contextOpt == null ? streamMetadataStore.createContext(scope, stream) : contextOpt;

        AtomicReference<UpdateStreamEvent> eventRef = new AtomicReference<>(null);
        // 1. get configuration
        return streamMetadataStore.getConfigurationWithVersion(scope, stream, context, executor)
                // 2. post event with configuration update + version
                .thenCompose(configWithVersion -> {
                    System.err.println();
                    return postUpdateEvent(scope, stream, config, configWithVersion)
                            .thenAccept(eventRef::set);
                })
                // 3. set state to updating
                .thenCompose(x -> streamMetadataStore.setState(scope, stream, State.UPDATING, context, executor))
                // 4. respond to client that update is being processed
                .thenCompose(result -> {
                    if (result) {
                        return checkUpdated(scope, stream, config, eventRef.get())
                                .thenApply(x -> UpdateStreamStatus.Status.SUCCESS);
                    } else {
                        return CompletableFuture.completedFuture(UpdateStreamStatus.Status.FAILURE);
                    }
                })
                .exceptionally(ex -> {
                    log.warn("Exception thrown in trying to update stream configuration {}", ex.getMessage());
                    return handleUpdateStreamError(ex);
                });
    }