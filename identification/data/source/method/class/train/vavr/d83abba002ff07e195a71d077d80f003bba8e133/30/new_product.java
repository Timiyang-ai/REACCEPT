public static Queue<Double> ofAll(double[] array) {
        Objects.requireNonNull(array, "array is null");
        return ofAll(List.ofAll(array));
    }