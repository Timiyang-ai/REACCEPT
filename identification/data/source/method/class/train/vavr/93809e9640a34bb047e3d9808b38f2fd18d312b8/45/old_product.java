public static <K extends Comparable<? super K>, V> TreeMap<K, V> of(Comparator<? super K> keyComparator, K k1, V v1, K k2, V v2, K k3, V v3) {
        return TreeMap.<K, V> empty(keyComparator).put(k1, v1).put(k2, v2).put(k3, v3);
    }