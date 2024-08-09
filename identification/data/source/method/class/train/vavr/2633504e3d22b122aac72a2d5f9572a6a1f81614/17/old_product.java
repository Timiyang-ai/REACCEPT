public static HashSet<Float> ofAll(float... array) {
        Objects.requireNonNull(array, "array is null");
        return HashSet.ofAll(Iterator.ofAll(array));
    }