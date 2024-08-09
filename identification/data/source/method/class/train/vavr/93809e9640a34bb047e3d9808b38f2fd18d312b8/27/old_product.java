@SuppressWarnings("varargs")
    @SafeVarargs
    public static <K extends Comparable<? super K>, V> TreeMap<K, V> ofEntries(java.util.Map.Entry<? extends K, ? extends V>... entries) {
        return ofEntries((Comparator<? super K> & Serializable) K::compareTo, entries);
    }