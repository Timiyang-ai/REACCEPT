public static LinkedHashSet<Short> ofAll(short... array) {
        Objects.requireNonNull(array, "array is null");
        return LinkedHashSet.ofAll(Iterator.ofAll(array));
    }