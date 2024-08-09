public static Vector<Float> ofAll(float[] array) {
        Objects.requireNonNull(array, "array is null");
        return ofAll(Iterator.ofAll(array));
    }