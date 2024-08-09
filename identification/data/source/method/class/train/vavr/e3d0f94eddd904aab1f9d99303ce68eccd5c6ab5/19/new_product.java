public static TreeSet<Character> ofAll(char... elements) {
        Objects.requireNonNull(elements, "elements is null");
        return TreeSet.ofAll(Iterator.ofAll(elements));
    }