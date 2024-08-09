@SuppressWarnings("unchecked")
    public static <K, V> TreeMap<K, V> of(Object... pairs) {
        Objects.requireNonNull(pairs, "pairs is null");
        if ((pairs.length & 1) != 0) {
            throw new IllegalArgumentException("Odd length of key-value pairs list");
        }
        RedBlackTree<Tuple2<K, V>> result = RedBlackTree.empty(EntryComparator.of(naturalComparator()));
        for (int i = 0; i < pairs.length; i += 2) {
            result = result.insert(Tuple.of((K) pairs[i], (V) pairs[i + 1]));
        }
        return new TreeMap<>(result);
    }