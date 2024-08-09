@SuppressWarnings("unchecked")
    public static <T> HashSet<T> ofAll(Iterable<? extends T> entries) {
        Objects.requireNonNull(entries, "entries is null");
        if (entries instanceof HashSet) {
            return (HashSet<T>) entries;
        } else {
            HashArrayMappedTrie<T, Object> tree = HashArrayMappedTrie.empty();
            for (T entry : entries) {
                tree = tree.put(entry, entry);
            }
            return tree.isEmpty() ? empty() : new HashSet<>(tree);
        }
    }