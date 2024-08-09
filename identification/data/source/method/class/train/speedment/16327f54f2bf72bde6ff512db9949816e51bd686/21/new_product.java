public void forEach(BiConsumer<? super K, ? super V> action) {
        requireNonNull(action);
        inner.forEach(e -> action.accept(e.getKey(), e.getValue()));
    }