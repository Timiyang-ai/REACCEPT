public static Array<Short> ofAll(short[] array) {
        Objects.requireNonNull(array, "array is null");
        return Array.ofAll(Iterator.ofAll(array));
    }