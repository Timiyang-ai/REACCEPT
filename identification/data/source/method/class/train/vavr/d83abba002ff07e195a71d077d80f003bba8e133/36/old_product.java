public static Queue<Long> ofAll(long... array) {
        Objects.requireNonNull(array, "array is null");
        return ofAll(List.ofAll(array));
    }