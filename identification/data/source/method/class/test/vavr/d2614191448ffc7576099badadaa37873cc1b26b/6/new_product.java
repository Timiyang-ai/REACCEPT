static Stream<Boolean> ofAll(boolean[] array) {
        Objects.requireNonNull(array, "array is null");
        return Stream.ofAll(() -> Iterator.ofAll(array));
    }