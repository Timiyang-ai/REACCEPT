@SuppressWarnings("checkstyle:RegexpMultiline") // Suppress VisibleForTesting warning
    @VisibleForTesting
    public void sweepNextBatch(ShardAndStrategy shardStrategy) {
        assertInitialized();
        if (!runSweep.get()) {
            metrics.registerOccurrenceOf(SweepOutcome.DISABLED);
            return;
        }
        long maxTsExclusive = Sweeper.of(shardStrategy).getSweepTimestamp(timestampsSupplier);
        queue.sweepNextBatch(shardStrategy, maxTsExclusive);
    }