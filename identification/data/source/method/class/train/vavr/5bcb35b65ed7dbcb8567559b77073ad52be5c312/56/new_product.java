public static Vector<Short> ofAll(short... array) {
        Objects.requireNonNull(array, "array is null");
        return ofAll(Iterator.ofAll(array));
    }