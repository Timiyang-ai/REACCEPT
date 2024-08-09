private Set<GridCacheVersion> awaitFinishOfPreparedTxs(
        long preparedTxsTimeout,
        long committingTxsTimeout
    ) throws IgniteCheckedException {
        IgniteInternalFuture<Set<GridCacheVersion>> fut;

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