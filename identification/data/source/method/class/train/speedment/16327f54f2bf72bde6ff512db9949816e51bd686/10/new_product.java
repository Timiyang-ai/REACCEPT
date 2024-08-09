@Override
    public MapStream<K, V> onClose(Runnable closeHandler) {
        inner = inner.onClose(requireNonNull(closeHandler));
        return this;
    }