public MapStream<K, V> sortedByKey(Comparator<K> comparator) {
        inner = inner.sorted(byKeyOnly(requireNonNull(comparator)));
        return this;
    }