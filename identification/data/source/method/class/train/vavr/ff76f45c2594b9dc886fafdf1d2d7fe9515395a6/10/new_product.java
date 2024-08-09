public static LinkedHashSet<Character> ofAll(char... elements) {
        Objects.requireNonNull(elements, "elements is null");
        return LinkedHashSet.ofAll(Iterator.ofAll(elements));
    }