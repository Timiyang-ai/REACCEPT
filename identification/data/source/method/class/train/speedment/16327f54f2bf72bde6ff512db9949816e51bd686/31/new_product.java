public SortedMap<K, V> toSortedMapByKey(Comparator<K> keyComparator) {
        requireNonNull(keyComparator);
        return inner.collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            throwingMerger(),
            () -> new TreeMap<>(keyComparator)
        ));
    }