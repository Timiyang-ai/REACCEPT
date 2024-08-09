public CompletableFuture<DeleteStreamStatus.Status> deleteStream(final String scope, final String stream,
                                                                     final OperationContext contextOpt) {
        final OperationContext context = contextOpt == null ? streamMetadataStore.createContext(scope, stream) : contextOpt;

        return streamMetadataStore.getState(scope, stream, false, context, executor)
                .thenCompose(state -> {
                    if (!state.equals(State.SEALED)) {
                        return CompletableFuture.completedFuture(false);
                    } else {
                        return writeEvent(new DeleteStreamEvent(scope, stream))
                                .thenApply(x -> true);
                    }
                })
                .thenCompose(result -> {
                    if (result) {
                        return checkDone(() -> isDeleted(scope, stream))
                                .thenApply(x -> DeleteStreamStatus.Status.SUCCESS);
                    } else {
                        return CompletableFuture.completedFuture(DeleteStreamStatus.Status.STREAM_NOT_SEALED);
                    }
                })
                .exceptionally(ex -> {
                    log.warn("Exception thrown while deleting stream", ex.getMessage());
                    return handleDeleteStreamError(ex);
                });
    }