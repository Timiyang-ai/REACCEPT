static List<Byte> ofAll(byte[] array) {
        Objects.requireNonNull(array, "array is null");
        return List.ofAll(() -> Iterator.ofAll(array));
    }