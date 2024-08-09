static BitSet<Long> ofAll(long[] array) {
        Objects.requireNonNull(array, "array is null");
        return BitSet.withLongs().ofAll(Iterator.ofAll(array));
    }