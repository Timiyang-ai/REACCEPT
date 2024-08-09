@Override
    public MapStream<K, V> sorted(Comparator<? super Map.Entry<K, V>> comparator) {
        inner = inner.sorted(comparator);
        return this;
    }