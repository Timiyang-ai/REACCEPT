public static HashSet<Character> ofAll(char[] array) {
        Objects.requireNonNull(array, "array is null");
        return HashSet.ofAll(Iterator.ofAll(array));
    }