public static BitSet<Integer> ofAll(int... elements) {
        Objects.requireNonNull(elements, "elements is null");
        return BitSet.ofAll(Iterator.ofAll(elements));
    }