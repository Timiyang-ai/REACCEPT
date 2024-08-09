public static <T> Vector<T> tabulate(Integer n, Function<Integer, ? extends T> f) {
        Objects.requireNonNull(n, "n is null");
        Objects.requireNonNull(f, "f is null");
        HashArrayMappedTrie<Integer, T> trie = HashArrayMappedTrie.empty();
        for (int i = 0; i <n; i++) {
            trie = trie.put(trie.size(), f.apply(i));
        }
        return new Vector<>(trie);
    }