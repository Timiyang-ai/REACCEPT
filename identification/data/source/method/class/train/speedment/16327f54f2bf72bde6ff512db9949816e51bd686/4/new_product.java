public Optional<Map.Entry<K, V>> maxByValue(Comparator<V> comparator) {
        return inner.max(byValueOnly(requireNonNull(comparator)));
    }