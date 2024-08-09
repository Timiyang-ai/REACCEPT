static BitSet<Character> ofAll(char... array) {
        Objects.requireNonNull(array, "array is null");
        return BitSet.withCharacters().ofAll(Iterator.ofAll(array));
    }