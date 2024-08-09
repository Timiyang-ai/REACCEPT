@SuppressWarnings("unchecked")
    public static <T> Vector<T> ofAll(Iterable<? extends T> iterable) {
        Objects.requireNonNull(iterable, "iterable is null");
        if (iterable instanceof Vector) {
            return (Vector<T>) iterable;
        } else {
            final BitMappedTrie<T> trie = BitMappedTrie.ofAll(asArray(iterable));
            return ofAll(trie);
        }
    }