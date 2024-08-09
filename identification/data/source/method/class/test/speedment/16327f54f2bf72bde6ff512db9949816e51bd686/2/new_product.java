public Optional<Map.Entry<K, V>> maxByKey(Comparator<K> comparator) {
        return inner.max(byKeyOnly(requireNonNull(comparator)));
    }