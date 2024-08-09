@SuppressWarnings("unchecked")
    public static <T> Vector<T> ofAll(Iterable<? extends T> iterable) {
        Objects.requireNonNull(iterable, "iterable is null");
        if (iterable instanceof Vector) {
            return (Vector<T>) iterable;
        } else {
            BitMappedTrie<T> trie = BitMappedTrie.empty();
            for (T element : iterable) {
                trie = trie.append(element);
            }
            return ofAll(trie);
        }
    }