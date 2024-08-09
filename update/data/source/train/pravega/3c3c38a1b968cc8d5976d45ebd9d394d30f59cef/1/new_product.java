void process(Operation operation) throws DataCorruptionException {
        // Add entry to MemoryTransactionLog and ReadIndex/Cache. This callback is invoked from the QueueProcessor,
        // which always acks items in order of Sequence Number - so the entries should be ordered (but always check).
        CacheKey cacheKey = addToCache(operation);
        addToMemoryOperationLog(operation, cacheKey);
    }