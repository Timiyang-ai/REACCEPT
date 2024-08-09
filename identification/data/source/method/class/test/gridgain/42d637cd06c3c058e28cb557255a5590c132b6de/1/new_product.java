public Set<GridCacheVersion> currentlyPreparedTxs() {
        assert stateLock.writeLock().isHeldByCurrentThread();

        return U.sealSet(preparedCommittedTxsCounters.keySet());
    }