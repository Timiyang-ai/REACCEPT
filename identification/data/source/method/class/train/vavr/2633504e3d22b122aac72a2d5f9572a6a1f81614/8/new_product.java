public static HashSet<Short> ofAll(short... array) {
        Objects.requireNonNull(array, "array is null");
        return HashSet.ofAll(Iterator.ofAll(array));
    }