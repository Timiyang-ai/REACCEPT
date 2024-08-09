public static Array<Double> ofAll(double[] array) {
        Objects.requireNonNull(array, "array is null");
        return Array.ofAll(() -> Iterator.ofAll(array));
    }