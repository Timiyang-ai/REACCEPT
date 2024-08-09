static List<Long> ofAll(long[] array) {
        Objects.requireNonNull(array, "array is null");
        return List.ofAll(() -> Iterator.ofAll(array));
    }