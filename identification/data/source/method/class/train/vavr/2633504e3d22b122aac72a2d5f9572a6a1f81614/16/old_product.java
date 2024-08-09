static HashSet<Boolean> ofAll(boolean[] array) {
        Objects.requireNonNull(array, "array is null");
        return HashSet.ofAll(Iterator.ofAll(array));
    }