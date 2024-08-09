public static TreeSet<Long> ofAll(long[] array) {
        Objects.requireNonNull(array, "array is null");
        return TreeSet.ofAll(Iterator.ofAll(array));
    }