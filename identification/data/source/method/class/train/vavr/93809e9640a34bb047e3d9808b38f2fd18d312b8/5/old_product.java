public static <K extends Comparable<? super K>, V> TreeMap<K, V> ofEntries(Iterable<? extends Tuple2<? extends K, ? extends V>> entries) {
        return ofEntries((Comparator<? super K> & Serializable) K::compareTo, entries);
    }