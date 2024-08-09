public static Queue<Long> ofAll(long[] array) {
        Objects.requireNonNull(array, "array is null");
        return Queue.ofAll(List.ofAll(array));
    }