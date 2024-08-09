public static Queue<Integer> ofAll(int... array) {
        Objects.requireNonNull(array, "array is null");
        return ofAll(List.ofAll(array));
    }