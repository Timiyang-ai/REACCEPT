    @Test
    public void unpark_whenNoTimeoutSet() {
        WaitSet waitSet = newWaitSet();

        BlockedOperation op = new BlockedOperation();
        waitSet.park(op);

        assertEquals(1, waitSet.size());
        WaitSetEntry entry = waitSet.find(op);
        assertTrue(entry.isValid());
        assertFalse(entry.isExpired());
        assertFalse(entry.isCancelled());
    }