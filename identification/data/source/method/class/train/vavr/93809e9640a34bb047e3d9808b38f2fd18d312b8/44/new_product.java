public static <K extends Comparable<? super K>, V> TreeMap<K, V> ofAll(java.util.Map<? extends K, ? extends V> map) {
        Objects.requireNonNull(map, "map is null");
        RedBlackTree<Tuple2<K, V>> result = RedBlackTree.empty();
        for (java.util.Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            result = result.insert(Tuple.of(entry.getKey(), entry.getValue()));
        }
        return new TreeMap<>(result);
    }