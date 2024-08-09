private Map<GridCacheVersion, WALPointer> awaitFinishOfPreparedTxs(long preparedTxsTimeout,
        long committingTxsTimeout) throws IgniteCheckedException {
        IgniteInternalFuture<Map<GridCacheVersion, WALPointer>> fut;

        tracker.writeLockState();

        try {
            tracker.startTxFinishAwaiting(preparedTxsTimeout, committingTxsTimeout);

            fut = tracker.awaitPendingTxsFinished(Collections.emptySet());
        }
        finally {
            tracker.writeUnlockState();
        }

        return fut.get();
    }