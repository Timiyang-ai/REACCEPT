@SuppressWarnings("unchecked")
    public static <T> Vector<T> ofAll(Iterable<? extends T> elements) {
        Objects.requireNonNull(elements, "elements is null");
        if (elements instanceof Vector) {
            return (Vector<T>) elements;
        } else {
            BitMappedTrie<T> trie = BitMappedTrie.empty();
            for (T element : elements) {
                trie = trie.append(element);
            }
            return ofAll(trie);
        }
    }