public CompletableFuture<UpdateStreamStatus.Status> truncateStream(final String scope, final String stream,
                                                                       final Map<Integer, Long> streamCut,
                                                                       final OperationContext contextOpt) {
        final OperationContext context = contextOpt == null ? streamMetadataStore.createContext(scope, stream) : contextOpt;

        // 1. get stream cut
        return streamMetadataStore.getTruncationProperty(scope, stream, true, context, executor)
                .thenCompose(property -> {
                    if (!property.isUpdating()) {
                        // 2. post event with new stream cut if no truncation is ongoing
                        return writeEvent(new TruncateStreamEvent(scope, stream))
                                // 3. start truncation by updating the metadata
                                .thenCompose(x -> streamMetadataStore.startTruncation(scope, stream, streamCut,
                                        context, executor))
                                // 4. check for truncation to complete
                                .thenCompose(truncation -> checkDone(() -> isTruncated(scope, stream, streamCut, context))
                                        .thenApply(y -> UpdateStreamStatus.Status.SUCCESS));
                    } else {
                        log.warn("Another truncation in progress for {}/{}", scope, stream);
                        return CompletableFuture.completedFuture(UpdateStreamStatus.Status.FAILURE);
                    }
                })
                .exceptionally(ex -> {
                    log.warn("Exception thrown in trying to update stream configuration {}", ex);
                    return handleUpdateStreamError(ex);
                });
    }