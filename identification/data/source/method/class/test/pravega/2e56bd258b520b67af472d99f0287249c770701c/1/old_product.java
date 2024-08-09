void add(Operation operation) throws DataCorruptionException {
        CacheKey cacheKey = addToCache(operation);
        try {
            // Add entry to MemoryTransactionLog and ReadIndex. This callback is invoked from the QueueProcessor,
            // which always acks items in order of Sequence Number - so the entries should be ordered (but always check).
            if (!addToMemoryOperationLog(operation, cacheKey)) {
                // This is a pretty nasty one. It's safer to shut down the container than continue.
                // We either recorded the Operation correctly, but invoked this callback out of order, or we really
                // recorded the Operation in the wrong order (by sequence number). In either case, we will be inconsistent
                // while serving reads, so better stop now than later.
                throw new DataCorruptionException("About to have added a Log Operation to InMemoryOperationLog that was out of order.");
            }

            // Add entry to read index (if applicable).
            if (operation instanceof StorageOperation) {
                this.cacheUpdater.addToReadIndex((StorageOperation) operation, cacheKey);
            }
        } catch (Exception | Error ex) {
            if (cacheKey != null) {
                // Cleanup the cache after failing to process an operation that did add something to the cache.
                this.cacheUpdater.removeFromCache(cacheKey);
            }

            throw ex;
        }
    }