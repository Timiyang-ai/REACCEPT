static List<Integer> ofAll(int[] array) {
        Objects.requireNonNull(array, "array is null");
        return List.ofAll(() -> Iterator.ofAll(array));
    }