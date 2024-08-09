@Test(timeout = 10_000)
    public void testAwaitFinishOfPreparedTxs() throws Exception {
        txPrepare(1);

        txPrepare(2);
        txPrepare(2);

        txPrepare(3);
        txPrepare(3);
        txCommit(3);

        txPrepare(4);
        txCommit(4);

        txPrepare(5);
        txPrepare(5);
        txPrepare(5);
        txCommit(5);

        tracker.writeLockState();

        IgniteInternalFuture<Map<GridCacheVersion, WALPointer>> fut;
        try {
            tracker.startTxFinishAwaiting(1_000, 10_000);

            fut = tracker.awaitPendingTxsFinished(Collections.emptySet());
        }
        finally {
            tracker.writeUnlockState();
        }

        Thread.sleep(100);

        txCommit(5);
        txCommit(2);
        txCommit(2);

        long curTs = U.currentTimeMillis();

        Map<GridCacheVersion, WALPointer> pendingTxs = fut.get();

        assertTrue("Waiting for awaitFinishOfPreparedTxs future too long", U.currentTimeMillis() - curTs < 1_000);

        assertEquals(3, pendingTxs.size());
        assertTrue(pendingTxs.keySet().contains(nearXidVersion(1)));
        assertTrue(pendingTxs.keySet().contains(nearXidVersion(3)));
        assertTrue(pendingTxs.keySet().contains(nearXidVersion(5)));

        txCommit(1);
        txCommit(3);
        txCommit(5);

        tracker.writeLockState();

        try {
            tracker.startTxFinishAwaiting(1_000, 10_000);

            fut = tracker.awaitPendingTxsFinished(Collections.emptySet());
        }
        finally {
            tracker.writeUnlockState();
        }

        assertTrue(fut.get().isEmpty());
    }