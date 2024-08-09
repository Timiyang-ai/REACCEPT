@SuppressWarnings("unchecked")
    public static <T> HashSet<T> ofAll(java.lang.Iterable<? extends T> elements) {
        Objects.requireNonNull(elements, "elements is null");
        if (elements instanceof HashSet) {
            return (HashSet<T>) elements;
        } else {
            final HashArrayMappedTrie<T, T> tree = addAll(HashArrayMappedTrie.empty(), elements);
            return tree.isEmpty() ? empty() : new HashSet<>(tree);
        }
    }