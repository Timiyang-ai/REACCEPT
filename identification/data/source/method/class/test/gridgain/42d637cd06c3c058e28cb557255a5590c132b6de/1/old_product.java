public Map<GridCacheVersion, WALPointer> currentlyPreparedTxs() {
        assert stateLock.writeLock().isHeldByCurrentThread();

        return U.sealMap(currentlyPreparedTxs);
    }