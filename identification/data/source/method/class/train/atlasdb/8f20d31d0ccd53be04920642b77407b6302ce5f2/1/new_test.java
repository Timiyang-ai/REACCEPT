    private static LongStream getPartitionedTimestamps(TimestampRange timestampRange, int residue, int modulus) {
        return TimestampRanges.getPartitionedTimestamps(timestampRange, residue, modulus).stream();
    }