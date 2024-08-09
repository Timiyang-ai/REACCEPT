public static Array<Long> ofAll(long[] array) {
        Objects.requireNonNull(array, "array is null");
        return Array.ofAll(() -> Iterator.ofAll(array));
    }