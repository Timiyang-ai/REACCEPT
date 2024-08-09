static Stream<Long> ofAll(long[] array) {
        Objects.requireNonNull(array, "array is null");
        return Stream.ofAll(Iterator.ofAll(array));
    }