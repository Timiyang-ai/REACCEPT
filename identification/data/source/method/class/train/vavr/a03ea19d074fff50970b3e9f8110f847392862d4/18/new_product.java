static BitSet<Boolean> ofAll(boolean[] array) {
        Objects.requireNonNull(array, "array is null");
        return BitSet.withRelations(i -> i != 0, b -> b ? 1 : 0).ofAll(Iterator.ofAll(array));
    }