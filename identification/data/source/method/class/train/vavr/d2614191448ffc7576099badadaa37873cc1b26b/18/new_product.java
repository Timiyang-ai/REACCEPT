public static Stream<Float> ofAll(float... elements) {
        Objects.requireNonNull(elements, "elements is null");
        return Stream.ofAll(Iterator.ofAll(elements));
    }