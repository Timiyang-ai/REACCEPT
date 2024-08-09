@Test
    public void testCurrentlyPreparedTxs() {
        txPrepare(1);
        txKeyWrite(1, 10);
        txKeyWrite(1, 11);

        txPrepare(2);
        txKeyWrite(2, 20);
        txKeyWrite(2, 21);
        txKeyWrite(2, 22);

        txPrepare(3);
        txKeyWrite(3, 30);

        txCommit(2);

        tracker.writeLockState();

        try {
            Set<GridCacheVersion> currentlyPreparedTxs = tracker.currentlyPreparedTxs();

            assertEquals(2, currentlyPreparedTxs.size());
            assertTrue(currentlyPreparedTxs.contains(nearXidVersion(1)));
            assertTrue(currentlyPreparedTxs.contains(nearXidVersion(3)));
        }
        finally {
            tracker.writeUnlockState();
        }

        txKeyWrite(3, 31);
        txCommit(3);

        tracker.writeLockState();

        try {
            Set<GridCacheVersion> currentlyPreparedTxs = tracker.currentlyPreparedTxs();

            assertEquals(1, currentlyPreparedTxs.size());
            assertTrue(currentlyPreparedTxs.contains(nearXidVersion(1)));
        }
        finally {
            tracker.writeUnlockState();
        }
    }