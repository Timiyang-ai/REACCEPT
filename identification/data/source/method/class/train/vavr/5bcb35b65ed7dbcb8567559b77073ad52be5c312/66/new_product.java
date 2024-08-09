public static Vector<Character> ofAll(char[] array) {
        Objects.requireNonNull(array, "array is null");
        return Vector.ofAll(() -> Iterator.ofAll(array));
    }