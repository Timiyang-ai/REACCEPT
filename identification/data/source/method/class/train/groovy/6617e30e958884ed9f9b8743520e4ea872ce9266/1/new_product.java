@Override
    public V getAndPut(K key, ValueProvider<? super K, ? extends V> valueProvider) {
        return getAndPut(key, valueProvider, true);
    }