public List<LogEntry> truncateEntriesFrom(int entryIndex) {
        List<LogEntry> truncated = new ArrayList<LogEntry>();
        for (int i = logs.size() - 1; i >= toArrayIndex(entryIndex); i--) {
            truncated.add(logs.remove(i));
        }

        reverse(truncated);

        return truncated;
    }