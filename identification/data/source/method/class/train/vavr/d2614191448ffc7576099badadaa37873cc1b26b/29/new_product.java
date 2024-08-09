static Stream<Byte> ofAll(byte[] array) {
        Objects.requireNonNull(array, "array is null");
        return Stream.ofAll(() -> Iterator.ofAll(array));
    }