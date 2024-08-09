static Stream<Integer> ofAll(int[] array) {
        Objects.requireNonNull(array, "array is null");
        return Stream.ofAll(() -> Iterator.ofAll(array));
    }