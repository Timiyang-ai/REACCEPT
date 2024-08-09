static List<Short> ofAll(short[] array) {
        Objects.requireNonNull(array, "array is null");
        return List.ofAll(Iterator.ofAll(array));
    }