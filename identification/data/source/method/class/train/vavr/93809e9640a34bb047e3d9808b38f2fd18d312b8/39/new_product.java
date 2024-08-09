public static <K extends Comparable<? super K>, V> TreeMap<K, V> of(K k1, V v1, K k2, V v2) {
        return createFromPairs(EntryComparator.natural(), k1, v1, k2, v2);
    }