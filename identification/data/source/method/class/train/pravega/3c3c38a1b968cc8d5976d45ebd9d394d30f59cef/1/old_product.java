void process(Operation operation) throws DataCorruptionException {
        CacheKey cacheKey = addToCache(operation);
        try {
            // Add entry to MemoryTransactionLog and ReadIndex. This callback is invoked from the QueueProcessor,
            // which always acks items in order of Sequence Number - so the entries should be ordered (but always check).
            operation = addToMemoryOperationLog(operation, cacheKey);

            // Add entry to read index (if applicable).
            if (operation instanceof StorageOperation) {
                this.cacheUpdater.addToReadIndex((StorageOperation) operation);
            }
        } catch (Throwable ex) {
            if (!ExceptionHelpers.mustRethrow(ex)) {
                if (cacheKey != null) {
                    // Cleanup the cache after failing to process an operation that did process something to the cache.
                    this.cacheUpdater.removeFromCache(cacheKey);
                }
            }

            throw ex;
        }
    }