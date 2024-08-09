public List<LogEntry> truncateEntriesFrom(long entryIndex) {
        if (entryIndex <= snapshotIndex()) {
            throw new IllegalArgumentException("Illegal index: " + entryIndex + ", snapshot index: " + snapshotIndex());
        }
        if (entryIndex > lastLogOrSnapshotIndex()) {
            throw new IllegalArgumentException("Illegal index: " + entryIndex + ", last log index: " + lastLogOrSnapshotIndex());
        }

        long startSequence = toSequence(entryIndex);
        assert startSequence >= logs.headSequence() : "Entry index: " + entryIndex + ", Head Seq: " + logs.headSequence();

        List<LogEntry> truncated = new ArrayList<LogEntry>();
        for (long ix = startSequence; ix <= logs.tailSequence(); ix++) {
            truncated.add(logs.read(ix));
        }
        logs.setTailSequence(startSequence - 1);
        if (truncated.size() > 0) {
            dirty = true;
            try {
                logStore.truncateEntriesFrom(entryIndex);
            } catch (IOException e) {
                throw new HazelcastException(e);
            }
        }

        return truncated;
    }