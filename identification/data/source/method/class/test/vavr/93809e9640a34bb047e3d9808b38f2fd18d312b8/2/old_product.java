public static <K extends Comparable<? super K>, V> TreeMap<K, V> of(Comparator<? super K> keyComparator, K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        return TreeMap.<K, V> empty(keyComparator).put(k1, v1).put(k2, v2).put(k3, v3).put(k4, v4).put(k5, v5);
    }