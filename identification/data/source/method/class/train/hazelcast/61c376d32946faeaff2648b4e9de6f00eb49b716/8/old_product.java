public void appendEntries(LogEntry... newEntries) {
        int lastTerm = lastLogTerm();
        int lastIndex = lastLogIndex();

        for (LogEntry entry : newEntries) {
            if (entry.term() < lastTerm) {
                throw new IllegalArgumentException("Cannot append " + entry + " since its term is lower than last log term: "
                        + lastTerm);
            } else if (entry.index() != lastIndex + 1) {
                throw new IllegalArgumentException("Cannot append " + entry + "since its index is bigger than (lasLogIndex + 1): "
                        + (lastIndex + 1));
            }
            logs.add(entry);
            lastIndex++;
            lastTerm = Math.max(lastTerm, entry.term());
        }
    }