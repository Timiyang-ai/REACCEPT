public static <K extends Comparable<? super K>, V> TreeMap<K, V> of(K key, V value) {
        return createFromPairs(EntryComparator.natural(), key, value);
    }