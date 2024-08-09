@SuppressWarnings({ "unchecked", "varargs" })
    @SafeVarargs
    public static <K extends Comparable<? super K>, V> TreeMap<K, V> of(Entry<? extends K, ? extends V>... entries) {
        return of(K::compareTo, entries);
    }