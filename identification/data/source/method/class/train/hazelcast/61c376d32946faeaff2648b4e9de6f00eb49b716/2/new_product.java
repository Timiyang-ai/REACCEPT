public List<LogEntry> truncateEntriesFrom(long entryIndex) {
        if (entryIndex <= snapshotIndex()) {
            throw new IllegalArgumentException("Illegal index: " + entryIndex + ", snapshot index: " + snapshotIndex());
        }
        if (entryIndex > lastLogOrSnapshotIndex()) {
            throw new IllegalArgumentException("Illegal index: " + entryIndex + ", last log index: " + lastLogOrSnapshotIndex());
        }

        List<LogEntry> truncated = new ArrayList<LogEntry>();
        for (int i = logs.size() - 1, j = toArrayIndex(entryIndex); i >= j; i--) {
            truncated.add(logs.remove(i));
        }

        reverse(truncated);

        return truncated;
    }