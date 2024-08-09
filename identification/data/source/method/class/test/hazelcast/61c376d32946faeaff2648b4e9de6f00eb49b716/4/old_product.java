public List<LogEntry> setSnapshot(SnapshotEntry snapshot) {
        if (snapshot.index() <= snapshotIndex()) {
            throw new IllegalArgumentException("Illegal index: " + snapshot.index() + ", current snapshot index: "
                    + snapshotIndex());
        }

        List<LogEntry> truncated = new ArrayList<LogEntry>();
        reverse(logs);
        for (int i = logs.size() - 1; i >= 0; i--) {
            LogEntry logEntry = logs.get(i);
            if (logEntry.index() > snapshot.index()) {
                break;
            }

            logs.remove(i);
        }

        reverse(logs);

        this.snapshot = snapshot;

        return truncated;
    }