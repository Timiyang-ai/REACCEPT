public static LinkedHashSet<Boolean> ofAll(boolean... array) {
        Objects.requireNonNull(array, "array is null");
        return LinkedHashSet.ofAll(Iterator.ofAll(array));
    }