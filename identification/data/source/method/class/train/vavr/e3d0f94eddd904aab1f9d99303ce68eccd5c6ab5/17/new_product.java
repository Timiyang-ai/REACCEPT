public static TreeSet<Boolean> ofAll(boolean... elements) {
        Objects.requireNonNull(elements, "elements is null");
        return TreeSet.ofAll(Iterator.ofAll(elements));
    }