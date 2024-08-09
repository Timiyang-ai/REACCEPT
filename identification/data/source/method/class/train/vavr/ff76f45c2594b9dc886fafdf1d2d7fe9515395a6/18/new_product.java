public static LinkedHashSet<Integer> ofAll(int... elements) {
        Objects.requireNonNull(elements, "elements is null");
        return LinkedHashSet.ofAll(Iterator.ofAll(elements));
    }