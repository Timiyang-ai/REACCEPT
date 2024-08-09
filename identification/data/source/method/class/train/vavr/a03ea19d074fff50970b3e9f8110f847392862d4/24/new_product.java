static BitSet<Long> ofAll(long... elements) {
        Objects.requireNonNull(elements, "elements is null");
        return BitSet.withLongs().ofAll(Iterator.ofAll(elements));
    }