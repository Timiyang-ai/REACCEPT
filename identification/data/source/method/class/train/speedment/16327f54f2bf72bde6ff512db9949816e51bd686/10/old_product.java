@Override
    public MapStream<K, V> onClose(Runnable closeHandler) {
        inner = inner.onClose(closeHandler);
        return this;
    }