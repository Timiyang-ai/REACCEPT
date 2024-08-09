public static <K, V> TreeMap<K, V> of(Comparator<? super K> keyComparator, K k1, V v1, K k2, V v2, K k3, V v3) {
        return of(keyComparator, k1, v1, k2, v2).put(k3, v3);
    }