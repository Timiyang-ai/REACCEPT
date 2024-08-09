static <K extends Comparable<? super K>, V> HashMultimap<K, V> ofEntriesWithSortedSet(Iterable<? extends Tuple2<? extends K, ? extends V>> entries) {
        Objects.requireNonNull(entries, "entries is null");
        Multimap<K, V> result = emptyWithSortedSet();
        for (Tuple2<? extends K, ? extends V> entry : entries) {
            result = result.put(entry._1, entry._2);
        }
        return (HashMultimap<K, V>) result;
    }