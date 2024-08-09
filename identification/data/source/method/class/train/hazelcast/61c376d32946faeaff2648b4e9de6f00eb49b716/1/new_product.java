public void appendEntries(LogEntry... newEntries) {
        int lastTerm = lastLogOrSnapshotTerm();
        long lastIndex = lastLogOrSnapshotIndex();

        if (!checkAvailableCapacity(newEntries.length)) {
            throw new IllegalStateException("Not enough capacity! Capacity: " + logs.getCapacity()
                    + ", Size: " + logs.size() + ", New entries: " + newEntries.length);
        }

        for (LogEntry entry : newEntries) {
            if (entry.term() < lastTerm) {
                throw new IllegalArgumentException("Cannot append " + entry + " since its term is lower than last log term: "
                        + lastTerm);
            }
            if (entry.index() != lastIndex + 1) {
                throw new IllegalArgumentException("Cannot append " + entry
                        + " since its index is bigger than (lastLogIndex + 1): " + (lastIndex + 1));
            }
            logs.add(entry);
            try {
                logStore.appendEntry(entry);
            } catch (IOException e) {
                throw new HazelcastException(e);
            }
            lastIndex++;
            lastTerm = Math.max(lastTerm, entry.term());
        }

        dirty |= newEntries.length > 0;
    }