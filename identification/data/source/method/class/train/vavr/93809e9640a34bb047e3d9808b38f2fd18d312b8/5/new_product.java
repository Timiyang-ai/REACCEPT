public static <K extends Comparable<? super K>, V> TreeMap<K, V> ofEntries(Iterable<? extends Tuple2<? extends K, ? extends V>> entries) {
        return createTreeMap(EntryComparator.natural(), entries);
    }