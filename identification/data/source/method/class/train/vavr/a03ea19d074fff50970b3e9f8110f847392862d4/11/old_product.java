static BitSet<Short> ofAll(short... elements) {
        Objects.requireNonNull(elements, "elements is null");
        return BitSet.withShorts().ofAll(Iterator.ofAll(elements));
    }