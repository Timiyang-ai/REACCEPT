static Stream<Short> ofAll(short[] array) {
        Objects.requireNonNull(array, "array is null");
        return Stream.ofAll(Iterator.ofAll(array));
    }