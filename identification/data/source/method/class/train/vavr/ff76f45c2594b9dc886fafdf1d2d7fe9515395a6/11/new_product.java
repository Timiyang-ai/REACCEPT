public static LinkedHashSet<Integer> ofAll(int... array) {
        Objects.requireNonNull(array, "array is null");
        return LinkedHashSet.ofAll(Iterator.ofAll(array));
    }