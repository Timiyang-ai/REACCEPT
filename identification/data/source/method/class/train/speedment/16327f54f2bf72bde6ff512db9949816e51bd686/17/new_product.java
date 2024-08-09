public Optional<Map.Entry<K, V>> minByKey(Comparator<K> comparator) {
        return inner.min(byKeyOnly(requireNonNull(comparator)));
    }