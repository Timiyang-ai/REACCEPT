public MapStream<K, V> sortedByValue(Comparator<V> comparator) {
        inner = inner.sorted(byValueOnly(comparator));
        return this;
    }