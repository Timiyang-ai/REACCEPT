public static TreeSet<Double> ofAll(double[] array) {
        Objects.requireNonNull(array, "array is null");
        return TreeSet.ofAll(Iterator.ofAll(array));
    }