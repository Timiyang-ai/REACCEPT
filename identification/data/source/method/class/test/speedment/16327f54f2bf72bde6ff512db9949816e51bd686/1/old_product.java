@Override
    public void forEachOrdered(Consumer<? super Map.Entry<K, V>> action) {
        inner.forEachOrdered(action);
    }