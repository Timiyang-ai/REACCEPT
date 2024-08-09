static Stream<Double> ofAll(double[] array) {
        Objects.requireNonNull(array, "array is null");
        return Stream.ofAll(Iterator.ofAll(array));
    }