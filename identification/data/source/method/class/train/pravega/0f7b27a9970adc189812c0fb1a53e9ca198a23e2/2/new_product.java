CompletableFuture<Void> initialize(Duration timeout) {
        Exceptions.checkNotClosed(this.closed, this);
        Preconditions.checkState(!this.isInitialized, "SegmentAggregator has already been initialized.");

        return this.storage
                .getStreamSegmentInfo(this.metadata.getName(), timeout)
                .thenAccept(segmentInfo -> {
                    // Check & Update StorageLength in metadata.
                    if (this.metadata.getStorageLength() != segmentInfo.getLength()) {
                        if (this.metadata.getStorageLength() >= 0) {
                            // Only log warning if the StorageLength has actually been initialized, but is different.
                            log.warn("{}: SegmentMetadata has a StorageLength ({}) that is different than the actual one ({}) - updating metadata.", this.traceObjectId, this.metadata.getStorageLength(), segmentInfo.getLength());
                        }

                        // It is very important to keep this value up-to-date and correct.
                        this.metadata.setStorageLength(segmentInfo.getLength());
                    }

                    // Check if the Storage segment is sealed, but it's not in metadata (this is 100% indicative of some data corruption happening).
                    if (segmentInfo.isSealed()) {
                        if (!this.metadata.isSealed()) {
                            throw new RuntimeStreamingException(new DataCorruptionException(String.format("Segment '%s' is sealed in Storage but not in the metadata.", this.metadata.getName())));
                        }

                        if (!this.metadata.isSealedInStorage()) {
                            this.metadata.markSealedInStorage();
                            log.warn("{}: Segment is sealed in Storage but metadata does not reflect that - updating metadata.", this.traceObjectId, segmentInfo.getLength());
                        }
                    }

                    log.info("{}: Initialized. StorageLength = {}, Sealed = {}.", this.traceObjectId, segmentInfo.getLength(), segmentInfo.isSealed());
                    this.isInitialized = true;
                })
                .exceptionally(ex -> {
                    ex = ExceptionHelpers.getRealException(ex);
                    if (ex instanceof StreamSegmentNotExistsException) {
                        // Segment does not exist anymore. This is a real possibility during recovery, in the following cases:
                        // * We already processed a Segment Deletion but did not have a chance to checkpoint metadata
                        // * We processed a BatchMergeOperation but did not have a chance to ack/truncate the DataSource
                        this.metadata.markDeleted(); // Update metadata, just in case it is not already updated.
                        log.warn("{}: Segment does not exist in Storage. Ignoring all further operations on it.", this.traceObjectId, ex);
                    } else {
                        // Other kind of error - re-throw.
                        throw new CompletionException(ex);
                    }

                    this.isInitialized = true;
                    return null;
                });
    }