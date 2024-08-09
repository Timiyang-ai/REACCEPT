public static Queue<Float> ofAll(float[] array) {
        Objects.requireNonNull(array, "array is null");
        return ofAll(List.ofAll(array));
    }