public static TreeSet<Long> ofAll(long... elements) {
        Objects.requireNonNull(elements, "elements is null");
        return TreeSet.ofAll(Iterator.ofAll(elements));
    }