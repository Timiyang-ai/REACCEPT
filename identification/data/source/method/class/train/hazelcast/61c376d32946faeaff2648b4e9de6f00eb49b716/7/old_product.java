public List<LogEntry> truncateEntriesFrom(long entryIndex) {
        if (entryIndex <= snapshotIndex()) {
            throw new IllegalArgumentException("Illegal index: " + entryIndex + ", snapshot index: " + snapshotIndex());
        }
        if (entryIndex > lastLogOrSnapshotIndex()) {
            throw new IllegalArgumentException("Illegal index: " + entryIndex + ", last log index: " + lastLogOrSnapshotIndex());
        }

        long startSequence = toSequence(entryIndex);
        assert startSequence >= logs.headSequence() : "Entry index: " + entryIndex + ", Head Seq: " + logs.headSequence();

        List<LogEntry> truncated = new ArrayList<>();
        for (long ix = startSequence; ix <= logs.tailSequence(); ix++) {
            truncated.add(logs.read(ix));
        }
        logs.setTailSequence(startSequence - 1);

        return truncated;
    }