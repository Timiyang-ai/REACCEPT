public SortedMap<K, V> toSortedMap(Comparator<K> keyComparator, BinaryOperator<V> mergeFunction) {
        requireNonNull(keyComparator);
        requireNonNull(mergeFunction);
        return inner.collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            mergeFunction,
            () -> new TreeMap<>(keyComparator)
        ));
    }