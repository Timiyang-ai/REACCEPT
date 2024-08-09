public static <K extends Comparable<? super K>, V> TreeMap<K, V> empty() {
        return empty((Comparator<? super K> & Serializable) K::compareTo);
    }