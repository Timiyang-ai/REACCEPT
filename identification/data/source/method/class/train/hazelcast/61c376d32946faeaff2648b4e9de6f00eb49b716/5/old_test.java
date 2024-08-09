    @Test
    public void getEntriesBetween() {
        LogEntry[] entries = new LogEntry[] {
                new LogEntry(1, 1, null),
                new LogEntry(1, 2, null),
                new LogEntry(1, 3, null)
        };
        log.appendEntries(entries);

        LogEntry[] result = log.getEntriesBetween(1, 3);
        assertArrayEquals(entries, result);

        result = log.getEntriesBetween(1, 2);
        assertArrayEquals(Arrays.copyOfRange(entries, 0, 2), result);

        result = log.getEntriesBetween(2, 3);
        assertArrayEquals(Arrays.copyOfRange(entries, 1, 3), result);
    }