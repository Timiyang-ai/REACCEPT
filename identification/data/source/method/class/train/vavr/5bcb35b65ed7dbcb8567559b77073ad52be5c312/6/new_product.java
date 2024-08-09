public static Vector<Integer> ofAll(int... array) {
        Objects.requireNonNull(array, "array is null");
        return ofAll(Iterator.ofAll(array));
    }