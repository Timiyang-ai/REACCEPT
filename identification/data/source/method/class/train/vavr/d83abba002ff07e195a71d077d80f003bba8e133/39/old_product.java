public static Queue<Short> ofAll(short[] array) {
        Objects.requireNonNull(array, "array is null");
        return ofAll(List.ofAll(array));
    }