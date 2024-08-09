static BitSet<Short> ofAll(short... array) {
        Objects.requireNonNull(array, "array is null");
        return BitSet.withShorts().ofAll(Iterator.ofAll(array));
    }