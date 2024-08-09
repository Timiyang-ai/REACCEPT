public static HashSet<Long> ofAll(long[] array) {
        Objects.requireNonNull(array, "array is null");
        return HashSet.ofAll(Iterator.ofAll(array));
    }