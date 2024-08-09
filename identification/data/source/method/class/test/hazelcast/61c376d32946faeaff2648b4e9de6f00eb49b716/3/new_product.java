public LogEntry[] getEntriesBetween(long fromEntryIndex, long toEntryIndex) {
        if (fromEntryIndex > toEntryIndex) {
            throw new IllegalArgumentException("Illegal from entry index: " + fromEntryIndex + ", to entry index: "
                    + toEntryIndex);
        }
        if (!containsLogEntry(fromEntryIndex)) {
            throw new IllegalArgumentException("Illegal from entry index: " + fromEntryIndex);
        }
        if (fromEntryIndex > lastLogOrSnapshotIndex()) {
            throw new IllegalArgumentException("Illegal from entry index: " + fromEntryIndex + ", last log index: "
                    + lastLogOrSnapshotIndex());
        }
        if (toEntryIndex > lastLogOrSnapshotIndex()) {
            throw new IllegalArgumentException("Illegal to entry index: " + toEntryIndex + ", last log index: "
                    + lastLogOrSnapshotIndex());
        }

        assert ((int) (toEntryIndex - fromEntryIndex)) >= 0 : "Int overflow! From: " + fromEntryIndex + ", to: " + toEntryIndex;
        LogEntry[] entries = new LogEntry[(int) (toEntryIndex - fromEntryIndex + 1)];
        long offset = toSequence(fromEntryIndex);

        for (int i = 0; i < entries.length; i++) {
            entries[i] = logs.read(offset + i);
        }
        return entries;
    }