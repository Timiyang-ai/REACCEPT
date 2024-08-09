public LogEntry getLogEntry(long entryIndex) {
        if (entryIndex < 1) {
            throw new IllegalArgumentException("Illegal index: " + entryIndex + ". Index starts from 1.");
        }
        if (entryIndex > lastLogOrSnapshotIndex() || snapshotIndex() >= entryIndex) {
            return null;
        }

        return logs.get(toArrayIndex(entryIndex));
    }