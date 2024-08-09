    @Test
    public void setSnapshot() {
        LogEntry[] entries = new LogEntry[] {
                new LogEntry(1, 1, null),
                new LogEntry(1, 2, null),
                new LogEntry(1, 3, null),
                new LogEntry(1, 4, null),
                new LogEntry(1, 5, null),
                };
        log.appendEntries(entries);

        int truncated = log.setSnapshot(new SnapshotEntry(1, 3, null, 0, Collections.<RaftEndpoint>emptySet()));
        assertEquals(3, truncated);

        for (int i = 1; i <= 3 ; i++) {
            assertFalse(log.containsLogEntry(i));
            assertNull(log.getLogEntry(i));
        }
        for (int i = 4; i <= 5 ; i++) {
            assertTrue(log.containsLogEntry(i));
            assertNotNull(log.getLogEntry(i));
        }

        LogEntry lastLogEntry = log.lastLogOrSnapshotEntry();
        assertEquals(5, lastLogEntry.index());
        assertEquals(1, lastLogEntry.term());
        assertSame(lastLogEntry, log.getLogEntry(lastLogEntry.index()));
        assertEquals(log.lastLogOrSnapshotIndex(), 5);
        assertEquals(log.lastLogOrSnapshotTerm(), 1);
        assertEquals(log.snapshotIndex(), 3);

        LogEntry snapshot = log.snapshot();
        assertEquals(3, snapshot.index());
        assertEquals(1, snapshot.term());
    }