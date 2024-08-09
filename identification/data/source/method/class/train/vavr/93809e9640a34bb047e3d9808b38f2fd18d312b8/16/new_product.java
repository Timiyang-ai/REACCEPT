public static <K extends Comparable<? super K>, V> TreeMap<K, V> of(Entry<? extends K, ? extends V> entry) {
        return of((Comparator<? super K> & Serializable) K::compareTo, entry);
    }