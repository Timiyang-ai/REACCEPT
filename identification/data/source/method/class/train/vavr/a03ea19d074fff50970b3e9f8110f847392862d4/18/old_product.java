static BitSet<Boolean> ofAll(boolean[] array) {
        Objects.requireNonNull(array, "array is null");
        return BitSet.withRelations(
                (Function<Integer, Boolean> & Serializable) i -> i != 0,
                (Function<Boolean, Integer> & Serializable) b -> b ? 1 : 0
        ).ofAll(Iterator.ofAll(array));
    }