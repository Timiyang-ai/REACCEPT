public static TreeSet<Boolean> ofAll(boolean[] array) {
        Objects.requireNonNull(array, "array is null");
        return TreeSet.ofAll(Iterator.ofAll(array));
    }