public static PartitionedTimestamps getPartitionedTimestamps(TimestampRange range, int residue, int modulus) {
        OptionalLong startTimestamp = TimestampRanges.getLowestTimestampMatchingModulus(
                range,
                residue,
                modulus);

        if (!startTimestamp.isPresent()) {
            return ImmutablePartitionedTimestamps.builder()
                    .start(range.getLowerBound())
                    .interval(modulus)
                    .count(0)
                    .build();
        }

        OptionalLong endTimestamp = TimestampRanges.getHighestTimestampMatchingModulus(
                range,
                residue,
                modulus);

        long count = ((endTimestamp.getAsLong() - startTimestamp.getAsLong()) / modulus) + 1;

        return ImmutablePartitionedTimestamps.builder()
                .start(startTimestamp.getAsLong())
                .interval(modulus)
                .count(count)
                .build();
    }