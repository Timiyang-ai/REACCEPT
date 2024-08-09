public static <K extends Comparable<? super K>, V> TreeMap<K, V> of(Tuple2<? extends K, ? extends V> entry) {
        Objects.requireNonNull(entry, "entry is null");
        return createFromTuple(EntryComparator.natural(), entry);
    }