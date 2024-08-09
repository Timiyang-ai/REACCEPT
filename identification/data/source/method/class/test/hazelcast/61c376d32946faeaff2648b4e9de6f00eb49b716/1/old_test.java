    @Test
    public void truncateEntriesFrom() {
        LogEntry[] entries = new LogEntry[] {
                new LogEntry(1, 1, null),
                new LogEntry(1, 2, null),
                new LogEntry(1, 3, null),
                new LogEntry(1, 4, null)
        };
        log.appendEntries(entries);

        List<LogEntry> truncated = log.deleteEntriesFrom(3);
        assertEquals(2, truncated.size());
        assertArrayEquals(Arrays.copyOfRange(entries, 2, 4), truncated.toArray());

        for (int i = 1; i <= 2; i++) {
            assertEquals(entries[i - 1], log.getLogEntry(i));
        }

        assertNull(log.getLogEntry(3));
    }