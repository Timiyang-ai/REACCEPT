public CompletableFuture<UpdateStreamStatus.Status> updateStream(String scope, String stream, StreamConfiguration newConfig,
                                                                     OperationContext contextOpt) {
        final OperationContext context = contextOpt == null ? streamMetadataStore.createContext(scope, stream) : contextOpt;

        // 1. get configuration
        return streamMetadataStore.getConfigurationProperty(scope, stream, true, context, executor)
                .thenCompose(configProperty -> {
                    // 2. post event to start update workflow
                    if (!configProperty.isUpdating()) {
                        return writeEvent(new UpdateStreamEvent(scope, stream))
                                // 3. update new configuration in the store with updating flag = true
                                // if attempt to update fails, we bail out with no harm done
                                .thenCompose(x -> streamMetadataStore.startUpdateConfiguration(scope, stream, newConfig,
                                        context, executor))
                                // 4. wait for update to complete
                                .thenCompose(x -> checkDone(() -> isUpdated(scope, stream, newConfig, context))
                                        .thenApply(y -> UpdateStreamStatus.Status.SUCCESS));
                    } else {
                        log.warn("Another update in progress for {}/{}", scope, stream);
                        return CompletableFuture.completedFuture(UpdateStreamStatus.Status.FAILURE);
                    }
                })
                .exceptionally(ex -> {
                    log.warn("Exception thrown in trying to update stream configuration {}", ex.getMessage());
                    return handleUpdateStreamError(ex);
                });
    }