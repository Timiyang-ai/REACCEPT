public static Queue<Double> ofAll(double[] array) {
        Objects.requireNonNull(array, "array is null");
        return Queue.ofAll(List.ofAll(array));
    }