static Stream<Float> ofAll(float[] array) {
        Objects.requireNonNull(array, "array is null");
        return Stream.ofAll(Iterator.ofAll(array));
    }