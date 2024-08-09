public static Array<Character> ofAll(char[] array) {
        Objects.requireNonNull(array, "array is null");
        return Array.ofAll(() -> Iterator.ofAll(array));
    }