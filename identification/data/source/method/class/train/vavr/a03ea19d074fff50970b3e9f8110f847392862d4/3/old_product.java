static BitSet<Integer> ofAll(int... array) {
        Objects.requireNonNull(array, "array is null");
        return BitSet.ofAll(Iterator.ofAll(array));
    }