public static Queue<Boolean> ofAll(boolean... array) {
        Objects.requireNonNull(array, "array is null");
        return ofAll(List.ofAll(array));
    }