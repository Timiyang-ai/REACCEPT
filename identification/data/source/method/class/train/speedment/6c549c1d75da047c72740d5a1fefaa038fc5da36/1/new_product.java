public ConcurrentNavigableMap<K, V> toConcurrentNavigableMapByKey(Comparator<K> keyComparator) {
        return inner.collect(Collectors.toConcurrentMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            throwingMerger(),
            () -> new ConcurrentSkipListMap<>(keyComparator)
        ));
    }