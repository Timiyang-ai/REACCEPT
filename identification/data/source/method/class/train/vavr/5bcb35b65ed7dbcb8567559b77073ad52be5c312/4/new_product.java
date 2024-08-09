public static Vector<Short> ofAll(short... elements) {
        Objects.requireNonNull(elements, "elements is null");
        return ofAll(BitMappedTrie.ofAll(elements));
    }