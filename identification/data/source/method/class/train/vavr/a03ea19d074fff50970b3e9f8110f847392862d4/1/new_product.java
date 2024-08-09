public static BitSet<Boolean> ofAll(boolean... elements) {
        Objects.requireNonNull(elements, "elements is null");
        return BitSet.withRelations(i -> i != 0, b -> b ? 1 : 0).ofAll(Iterator.ofAll(elements));
    }