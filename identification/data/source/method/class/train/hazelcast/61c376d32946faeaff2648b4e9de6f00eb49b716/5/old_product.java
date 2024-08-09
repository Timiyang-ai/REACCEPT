public LogEntry[] getEntriesBetween(int fromEntryIndex, int toEntryIndex) {
        if (logs.size() < fromEntryIndex) {
            return new LogEntry[0];
        }

        return logs.subList(toArrayIndex(fromEntryIndex), toArrayIndex(toEntryIndex + 1)).toArray(new LogEntry[0]);
    }