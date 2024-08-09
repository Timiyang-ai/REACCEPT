@SuppressWarnings("unchecked")
    public static <T> Vector<T> ofAll(Iterable<? extends T> elements) {
        Objects.requireNonNull(elements, "elements is null");
        if (elements instanceof Vector) {
            return (Vector<T>) elements;
        } else {
            HashArrayMappedTrie<Integer, T> trie = HashArrayMappedTrie.empty();
            for (T element : elements) {
                trie = trie.put(trie.size(), element);
            }
            return new Vector<>(trie);
        }
    }