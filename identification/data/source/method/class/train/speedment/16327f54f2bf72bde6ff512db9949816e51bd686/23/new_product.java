public MapStream<K, V> peek(BiConsumer<? super K, ? super V> action) {
        requireNonNull(action);
        inner = inner.peek(e -> action.accept(e.getKey(), e.getValue()));
        return this;
    }