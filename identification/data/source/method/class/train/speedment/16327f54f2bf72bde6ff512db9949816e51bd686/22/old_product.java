public Optional<Map.Entry<K, V>> minByValue(Comparator<V> comparator) {
        return inner.min(byValueOnly(comparator));
    }