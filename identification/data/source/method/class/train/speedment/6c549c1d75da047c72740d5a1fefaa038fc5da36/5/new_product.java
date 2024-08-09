public ConcurrentNavigableMap<K, V> toConcurrentNavigableMap(BinaryOperator<V> mergeFunction) {
        requireNonNull(mergeFunction);
        return inner.collect(Collectors.toConcurrentMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            mergeFunction,
            ConcurrentSkipListMap::new
        ));
    }