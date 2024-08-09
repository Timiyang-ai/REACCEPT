public static Stream<Long> ofAll(long... elements) {
        Objects.requireNonNull(elements, "elements is null");
        return Stream.ofAll(Iterator.ofAll(elements));
    }