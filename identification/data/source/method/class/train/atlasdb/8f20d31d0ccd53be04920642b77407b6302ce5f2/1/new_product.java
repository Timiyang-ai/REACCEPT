public static PartitionedTimestamps getPartitionedTimestamps(TimestampRange range, int residue, int modulus) {
        checkModulusAndResidue(residue, modulus);

        long startTimestamp = getLowestTimestampMatchingModulus(range.getLowerBound(), residue, modulus);
        long endTimestamp = range.getUpperBound() % modulus == residue ? range.getUpperBound() :
                getLowestTimestampMatchingModulus(range.getUpperBound(), residue, modulus) - modulus;

        if (startTimestamp > endTimestamp) {
            return ImmutablePartitionedTimestamps.builder()
                    .start(range.getLowerBound())
                    .interval(modulus)
                    .count(0)
                    .build();
        }

        int count = (int) ((endTimestamp - startTimestamp) / modulus) + 1;

        return ImmutablePartitionedTimestamps.builder()
                .start(startTimestamp)
                .interval(modulus)
                .count(count)
                .build();
    }