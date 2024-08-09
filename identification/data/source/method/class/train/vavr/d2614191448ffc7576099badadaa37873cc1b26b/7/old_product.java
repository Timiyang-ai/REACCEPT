static Stream<Character> ofAll(char[] array) {
        Objects.requireNonNull(array, "array is null");
        return Stream.ofAll(() -> Iterator.ofAll(array));
    }