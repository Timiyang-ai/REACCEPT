public void forEachOrdered(BiConsumer<? super K, ? super V> action) {
        requireNonNull(action);
        inner.forEachOrdered(e -> action.accept(e.getKey(), e.getValue()));
    }