public SortedMap<K, V> toSortedMap(Comparator<K> keyComparator) {
        return inner.collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            throwingMerger(),
            () -> new TreeMap<>(keyComparator)
        ));
    }