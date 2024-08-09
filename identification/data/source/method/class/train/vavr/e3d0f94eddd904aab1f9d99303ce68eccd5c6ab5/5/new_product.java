public static TreeSet<Integer> ofAll(int[] array) {
        Objects.requireNonNull(array, "array is null");
        return TreeSet.ofAll(Iterator.ofAll(array));
    }