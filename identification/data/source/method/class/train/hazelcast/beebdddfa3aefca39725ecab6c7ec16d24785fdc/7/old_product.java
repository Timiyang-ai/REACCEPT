public LogEntry getEntry(int entryIndex) {
        if (entryIndex < 1) {
            throw new IllegalArgumentException("Illegal index: " + entryIndex + ". Index starts from 1.");
        }
        return logs.size() >= entryIndex ? logs.get(toArrayIndex(entryIndex)) : null;
    }