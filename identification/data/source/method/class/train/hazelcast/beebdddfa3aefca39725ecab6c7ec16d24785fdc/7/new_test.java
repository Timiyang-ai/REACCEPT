    @Test
    public void getEntry() {
        LogEntry[] entries = new LogEntry[] {
                new LogEntry(1, 1, null),
                new LogEntry(1, 2, null),
                new LogEntry(1, 3, null)
        };
        log.appendEntries(entries);

        for (int i = 1; i <= log.lastLogOrSnapshotIndex(); i++) {
            LogEntry entry = log.getLogEntry(i);
            assertEquals(1, entry.term());
            assertEquals(i, entry.index());
        }
    }