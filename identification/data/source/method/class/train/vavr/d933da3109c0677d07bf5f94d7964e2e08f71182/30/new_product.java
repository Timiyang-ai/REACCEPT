public static Array<Float> ofAll(float[] array) {
        Objects.requireNonNull(array, "array is null");
        return Array.ofAll(Iterator.ofAll(array));
    }