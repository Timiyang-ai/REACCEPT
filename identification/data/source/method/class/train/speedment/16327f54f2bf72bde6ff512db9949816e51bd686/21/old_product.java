public void forEach(BiConsumer<? super K, ? super V> action) {
        inner.forEach(e -> action.accept(e.getKey(), e.getValue()));
    }