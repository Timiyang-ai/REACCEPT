public static <K extends Comparable<? super K>, V> TreeMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6) {
        return of((Comparator<? super K> & Serializable) K::compareTo, k1, v1, k2, v2, k3, v3, k4, v4, k5, v5, k6, v6);
    }