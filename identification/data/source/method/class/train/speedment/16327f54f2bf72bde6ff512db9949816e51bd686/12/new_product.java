public MapStream<K, V> sortedByValue(Comparator<V> comparator) {
        inner = inner.sorted(byValueOnly(requireNonNull(comparator)));
        return this;
    }