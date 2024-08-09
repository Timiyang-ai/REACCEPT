public static <K, V> TreeMap<K, V> of(Comparator<? super K> keyComparator, K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        return of(keyComparator, k1, v1, k2, v2, k3, v3).put(k4, v4);
    }