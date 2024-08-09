@SuppressWarnings("unchecked")
    public static <K extends Comparable<? super K>, V> TreeMap<K, V> ofAll(java.lang.Iterable<? extends Entry<? extends K, ? extends V>> entries) {
        return ofAll(K::compareTo, entries);
    }