public static <K extends Comparable<? super K>, V> TreeMap<K, V> ofAll(
            Iterable<? extends Tuple2<? extends K, ? extends V>> entries) {
        return ofAll((Comparator<? super K> & Serializable) K::compareTo, entries);
    }