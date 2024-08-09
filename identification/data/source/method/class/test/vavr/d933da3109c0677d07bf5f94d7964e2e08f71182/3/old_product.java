public static Array<Boolean> ofAll(boolean[] array) {
        Objects.requireNonNull(array, "array is null");
        return Array.ofAll(() -> Iterator.ofAll(array));
    }