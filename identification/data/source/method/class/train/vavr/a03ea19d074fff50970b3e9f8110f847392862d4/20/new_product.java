static BitSet<Character> ofAll(char... elements) {
        Objects.requireNonNull(elements, "elements is null");
        return BitSet.withCharacters().ofAll(Iterator.ofAll(elements));
    }