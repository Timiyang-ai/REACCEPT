static List<Character> ofAll(char[] array) {
        Objects.requireNonNull(array, "array is null");
        return List.ofAll(Iterator.ofAll(array));
    }