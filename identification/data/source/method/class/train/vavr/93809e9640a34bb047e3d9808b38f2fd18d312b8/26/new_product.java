public static <K extends Comparable<? super K>, V> TreeMap<K, V> of(K key, V value) {
        return of((Comparator<? super K> & Serializable) K::compareTo, key, value);
    }