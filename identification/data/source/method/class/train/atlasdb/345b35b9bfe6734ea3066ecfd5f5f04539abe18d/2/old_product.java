@VisibleForTesting
    public void sweepNextBatch(ShardAndStrategy shardStrategy) {
        if (!runSweep.get()) {
            return;
        }
        long maxTsExclusive = Sweeper.of(shardStrategy).getSweepTimestamp(timestampsSupplier);
        queue.sweepNextBatch(shardStrategy, maxTsExclusive);
    }