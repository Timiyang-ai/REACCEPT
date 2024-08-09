@SafeVarargs
    public static <T> HashSet<T> of(T... elements) {
        Objects.requireNonNull(elements, "elements is null");
        HashArrayMappedTrie<T, T> tree = HashArrayMappedTrie.empty();
        for (T element : elements) {
            tree = tree.put(element, element);
        }
        return new HashSet<>(tree);
    }