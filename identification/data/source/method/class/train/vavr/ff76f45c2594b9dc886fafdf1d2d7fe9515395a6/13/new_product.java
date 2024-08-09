public static LinkedHashSet<Double> ofAll(double... array) {
        Objects.requireNonNull(array, "array is null");
        return LinkedHashSet.ofAll(Iterator.ofAll(array));
    }