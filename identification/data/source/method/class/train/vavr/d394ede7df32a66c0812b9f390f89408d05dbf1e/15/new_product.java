static List<Double> ofAll(double[] array) {
        Objects.requireNonNull(array, "array is null");
        return List.ofAll(Iterator.ofAll(array));
    }