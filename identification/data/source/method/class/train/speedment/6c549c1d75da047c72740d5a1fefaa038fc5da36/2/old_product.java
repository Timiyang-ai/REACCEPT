public ConcurrentNavigableMap<K, V> toConcurrentNavigableMap(Comparator<K> keyComparator, BinaryOperator<V> mergeFunction) {
        return inner.collect(Collectors.toConcurrentMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            mergeFunction,
            () -> new ConcurrentSkipListMap<>(keyComparator)
        ));
    }