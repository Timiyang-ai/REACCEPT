CompletableFuture<Void> initialize(Duration timeout, Executor executor) {
        Exceptions.checkNotClosed(isClosed(), this);
        Preconditions.checkState(this.state.get() == AggregatorState.NotInitialized, "SegmentAggregator has already been initialized.");
        assert this.handle.get() == null : "non-null handle but state == " + this.state.get();
        long traceId = LoggerHelpers.traceEnterWithContext(log, this.traceObjectId, "initialize");

        return openWrite(this.metadata.getName(), this.handle, executor, timeout)
                .thenAccept(segmentInfo -> {
                    // Check & Update StorageLength in metadata.
                    if (this.metadata.getStorageLength() != segmentInfo.getLength()) {
                        if (this.metadata.getStorageLength() >= 0) {
                            // Only log warning if the StorageLength has actually been initialized, but is different.
                            log.warn("{}: SegmentMetadata has a StorageLength ({}) that is different than the actual one ({}) - updating metadata.", this.traceObjectId, this.metadata.getStorageLength(), segmentInfo.getLength());
                        }

                        // It is very important to keep this value up-to-date and correct.
                        this.metadata.setStorageLength(segmentInfo.getLength());
                        this.dataSource.notifyStorageLengthUpdated(this.metadata.getId());
                    }

                    // Check if the Storage segment is sealed, but it's not in metadata (this is 100% indicative of some data corruption happening).
                    if (segmentInfo.isSealed()) {
                        if (!this.metadata.isSealed()) {
                            throw new CompletionException(new DataCorruptionException(String.format("Segment '%s' is sealed in Storage but not in the metadata.", this.metadata.getName())));
                        }

                        if (!this.metadata.isSealedInStorage()) {
                            this.metadata.markSealedInStorage();
                            log.warn("{}: Segment is sealed in Storage but metadata does not reflect that - updating metadata.", this.traceObjectId, segmentInfo.getLength());
                        }
                    }

                    log.info("{}: Initialized. StorageLength = {}, Sealed = {}.", this.traceObjectId, segmentInfo.getLength(), segmentInfo.isSealed());
                    LoggerHelpers.traceLeave(log, this.traceObjectId, "initialize", traceId);
                    setState(AggregatorState.Writing);
                })
                .exceptionally(ex -> {
                    ex = ExceptionHelpers.getRealException(ex);
                    if (ex instanceof StreamSegmentNotExistsException) {
                        // Segment does not exist anymore. This is a real possibility during recovery, in the following cases:
                        // * We already processed a Segment Deletion but did not have a chance to checkpoint metadata
                        // * We processed a TransactionMergeOperation but did not have a chance to ack/truncate the DataSource
                        this.metadata.markDeleted(); // Update metadata, just in case it is not already updated.
                        log.warn("{}: Segment does not exist in Storage. Ignoring all further operations on it.", this.traceObjectId, ex);
                        setState(AggregatorState.Writing);
                        LoggerHelpers.traceLeave(log, this.traceObjectId, "initialize", traceId);
                    } else {
                        // Other kind of error - re-throw.
                        throw new CompletionException(ex);
                    }

                    return null;
                });
    }