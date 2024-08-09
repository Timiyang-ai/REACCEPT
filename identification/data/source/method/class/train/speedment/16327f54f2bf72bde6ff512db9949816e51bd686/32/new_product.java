@Override
    public MapStream<K, V> filter(Predicate<? super Map.Entry<K, V>> predicate) {
        inner = inner.filter(requireNonNull(predicate));
        return this;
    }