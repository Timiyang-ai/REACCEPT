public static <K extends Comparable<? super K>, V> TreeMap<K, V> of(Comparator<? super K> keyComparator, K key, V value) {
        Objects.requireNonNull(keyComparator, "keyComparator is null");
        Objects.requireNonNull(key, "key is null");
        Objects.requireNonNull(value, "value is null");
        return TreeMap.<K, V>empty(keyComparator).put(key, value);
    }