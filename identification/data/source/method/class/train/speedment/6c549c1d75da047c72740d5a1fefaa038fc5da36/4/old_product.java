public SortedMap<K, V> toSortedMap(BinaryOperator<V> mergeFunction) {
        return inner.collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            mergeFunction,
            TreeMap::new
        ));
    }