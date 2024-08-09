public static Queue<Character> ofAll(char... array) {
        Objects.requireNonNull(array, "array is null");
        return ofAll(List.ofAll(array));
    }