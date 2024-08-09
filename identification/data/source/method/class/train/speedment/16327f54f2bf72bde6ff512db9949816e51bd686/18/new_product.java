@Override
    public void forEach(Consumer<? super Map.Entry<K, V>> action) {
        inner.forEach(requireNonNull(action));
    }