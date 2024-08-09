@Override
    public MapStream<K, V> sorted(Comparator<? super Map.Entry<K, V>> comparator) {
        inner = inner.sorted(requireNonNull(comparator));
        return this;
    }