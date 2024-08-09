@SuppressWarnings("checkstyle:RegexpMultiline") // Suppress VisibleForTesting warning
    @VisibleForTesting
    public boolean sweepNextBatch(ShardAndStrategy shardStrategy, long maxTsExclusive) {
        assertInitialized();
        return queue.sweepNextBatch(shardStrategy, maxTsExclusive);
    }