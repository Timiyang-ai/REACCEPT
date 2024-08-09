@Deprecated
    @SuppressWarnings("unchecked")
    public static <K extends Comparable<? super K>, V> TreeMap<K, V> of(Object... pairs) {
        return of((Comparator<? super K> & Serializable) K::compareTo, pairs);
    }