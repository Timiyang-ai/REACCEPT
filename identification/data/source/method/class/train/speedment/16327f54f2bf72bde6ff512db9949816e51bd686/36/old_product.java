public void forEachOrdered(BiConsumer<? super K, ? super V> action) {
        inner.forEachOrdered(e -> action.accept(e.getKey(), e.getValue()));
    }