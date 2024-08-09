static List<Boolean> ofAll(boolean[] array) {
        Objects.requireNonNull(array, "array is null");
        return List.ofAll(() -> Iterator.ofAll(array));
    }