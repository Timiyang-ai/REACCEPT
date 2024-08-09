@Override
    public MapStream<K, V> peek(Consumer<? super Map.Entry<K, V>> action) {
        inner = inner.peek(requireNonNull(action));
        return this;
    }