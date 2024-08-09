public List<LogEntry> deleteEntriesFrom(long entryIndex) {
        if (entryIndex <= snapshotIndex()) {
            throw new IllegalArgumentException(String.format(
                    "Illegal index %,d, snapshot index is %,d", entryIndex, snapshotIndex()));
        }
        if (entryIndex > lastLogOrSnapshotIndex()) {
            throw new IllegalArgumentException(String.format(
                    "Illegal index %,d, last log index is %,d", entryIndex, lastLogOrSnapshotIndex()));
        }

        long startSequence = toSequence(entryIndex);
        assert startSequence >= logs.headSequence() :
                "Entry index: " + entryIndex + ", Head Seq: " + logs.headSequence();

        List<LogEntry> truncated = new ArrayList<>();
        for (long ix = startSequence; ix <= logs.tailSequence(); ix++) {
            truncated.add(logs.read(ix));
        }
        logs.setTailSequence(startSequence - 1);
        if (truncated.size() > 0) {
            dirty = true;
            try {
                store.deleteEntriesFrom(entryIndex);
            } catch (IOException e) {
                throw new HazelcastException(e);
            }
        }

        return truncated;
    }