public LogEntry[] getEntriesBetween(long fromEntryIndex, long toEntryIndex) {
        if (fromEntryIndex > toEntryIndex) {
            throw new IllegalArgumentException("Illegal from entry index: " + fromEntryIndex + ", to entry index: "
                    + toEntryIndex);
        }
        if (fromEntryIndex <= snapshotIndex()) {
            throw new IllegalArgumentException("Illegal from entry index: " + fromEntryIndex + ", snapshot index: "
                    + snapshotIndex());
        }
        if (fromEntryIndex > lastLogOrSnapshotIndex()) {
            throw new IllegalArgumentException("Illegal from entry index: " + fromEntryIndex + ", last log index: "
                    + lastLogOrSnapshotIndex());
        }
        if (toEntryIndex > lastLogOrSnapshotIndex()) {
            throw new IllegalArgumentException("Illegal to entry index: " + toEntryIndex + ", last log index: "
                    + lastLogOrSnapshotIndex());
        }

        return logs.subList(toArrayIndex(fromEntryIndex), toArrayIndex(toEntryIndex + 1)).toArray(new LogEntry[0]);
    }