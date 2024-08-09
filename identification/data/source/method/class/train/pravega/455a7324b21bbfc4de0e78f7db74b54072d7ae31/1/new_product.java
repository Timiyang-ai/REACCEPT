public CompletableFuture<UpdateStreamStatus.Status> truncateStream(final String scope, final String stream,
                                                                       final Map<Long, Long> streamCut,
                                                                       final OperationContext contextOpt) {
        final OperationContext context = contextOpt == null ? streamMetadataStore.createContext(scope, stream) : contextOpt;
        final long requestId = requestTracker.getRequestIdFor("truncateStream", scope, stream);

        // 1. get stream cut
        return startTruncation(scope, stream, streamCut, context, requestId)
                // 4. check for truncation to complete
                .thenCompose(truncationStarted -> {
                    if (truncationStarted) {
                        return checkDone(() -> isTruncated(scope, stream, streamCut, context))
                                .thenApply(y -> UpdateStreamStatus.Status.SUCCESS);
                    } else {
                        log.warn(requestId, "Unable to start truncation for {}/{}", scope, stream);
                        return CompletableFuture.completedFuture(UpdateStreamStatus.Status.FAILURE);
                    }
                })
                .exceptionally(ex -> {
                    log.warn(requestId, "Exception thrown in trying to update stream configuration {}", ex);
                    return handleUpdateStreamError(ex, requestId);
                });
    }