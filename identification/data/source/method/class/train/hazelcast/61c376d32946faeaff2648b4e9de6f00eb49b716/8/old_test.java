    @Test
    public void test_appendEntries_withSameTerm() {
        log.appendEntries(new LogEntry(1, 1, null));
        log.appendEntries(new LogEntry(1, 2, null));
        LogEntry last = new LogEntry(1, 3, null);
        log.appendEntries(last);

        assertEquals(last.term(), log.lastLogOrSnapshotTerm());
        assertEquals(last.index(), log.lastLogOrSnapshotIndex());
    }