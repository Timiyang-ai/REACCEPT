@SuppressWarnings("unchecked")
    @SafeVarargs
    public static <K, V> TreeMap<K, V> ofEntries(Comparator<? super K> keyComparator, java.util.Map.Entry<? extends K, ? extends V>... entries) {
        Objects.requireNonNull(keyComparator, "keyComparator is null");
        Objects.requireNonNull(entries, "entries is null");
        RedBlackTree<Tuple2<K, V>> tree = RedBlackTree.empty(new EntryComparator<>(keyComparator));
        for (java.util.Map.Entry<? extends K, ? extends V> entry : entries) {
            tree = tree.insert(Tuple.of(entry.getKey(), entry.getValue()));
        }
        return new TreeMap<>(tree);
    }