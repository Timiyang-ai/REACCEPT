@Override
    public V getAndPut(K key, ValueProvider<K, V> valueProvider) {
        return getAndPut(key, valueProvider, true);
    }