public static Vector<Boolean> ofAll(boolean[] array) {
        Objects.requireNonNull(array, "array is null");
        return Vector.ofAll(Iterator.ofAll(array));
    }